package edu.scau.rorke.springbootdemo.service.impl;

import edu.scau.rorke.springbootdemo.dao.RoleDao;
import edu.scau.rorke.springbootdemo.dao.UserDao;
import edu.scau.rorke.springbootdemo.entity.User;
import edu.scau.rorke.springbootdemo.entity.dto.UserDto;
import edu.scau.rorke.springbootdemo.exception.NickNameAlreadyExistException;
import edu.scau.rorke.springbootdemo.service.SimpleRoleService;
import edu.scau.rorke.springbootdemo.service.UserProfileService;
import edu.scau.rorke.springbootdemo.utils.PasswordEncodingUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Rorke
 * @date 2020/2/27 15:51
 */
@Service
public class UserProfileServiceImpl extends SimpleRoleService implements UserProfileService {
    private UserDao userDao;
    private RedisTemplate<String, Serializable> redisTemplate;
    private final long CACHE_EXIST_TIME = TimeUnit.HOURS.toHours(3);
    /**
     * 注入面向持久层的dao和操作缓存的redisTemplate
     * controller做完数据处理之后，应该调用service来完成业务
     * service应该包含不同的逻辑完成各种业务
     * 包括调用dao访问数据库、调用缓存访问缓存等等
     * @param userDao   跟用户相关的dao
     * @param roleDao
     * @param redisTemplate 对redis缓存进行操作
     */
    @Autowired
    public UserProfileServiceImpl(UserDao userDao, RoleDao roleDao, RedisTemplate<String, Serializable> redisTemplate) {
        super(roleDao);
        this.userDao = userDao;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取用户的资料，缓存中没有，才在数据库中寻找
     * 将对象中的密码值设为空，或者可以创建一个dto，来进行数据传输
     * 这里采用了第2种方法
     * @param username 用户名
     * @return userDto，用户的数据传输对象
     */
    @Override
    public UserDto getUserProfileByUsername(String username){
        UserDto userDto = (UserDto) redisTemplate.opsForValue().get(USER_PROFILE_CACHE_PREFIX+username);
        if (userDto==null){
            userDto = new UserDto();
            User user = userDao.findByUserName(username);
            copyUserProperties(user,userDto);
            redisTemplate.opsForValue().set(USER_PROFILE_CACHE_PREFIX+username,userDto,CACHE_EXIST_TIME);
        }
        return userDto;
    }

    @Override
    public List<UserDto> getAllUser(int page, int size) {
        Page<User> users = userDao.findAll(PageRequest.of(page,size));
        return copyUsers(users.iterator());
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = userDao.findAll();
        return copyUsers(userList.iterator());
    }

    @Override
    public void saveUserProfile(String username, String nickname, String password) {
        User user = userDao.findByNickName(nickname);
        if(user!=null&&user.getUserName().equals(username)){
            throw new NickNameAlreadyExistException("用户昵称已经存在");
        }
        redisTemplate.delete(USER_PROFILE_CACHE_PREFIX+username);
        user = userDao.findByUserName(username);
        if (!password.equals("")){
            String saltedPassword = PasswordEncodingUtil.saltedPassword(user.getSalt(),password);
            user.setPassword(saltedPassword);
        }
        user.setNickName(nickname);
        userDao.saveAndFlush(user);
        UserDto userDto = new UserDto();
        copyUserProperties(user,userDto);
        redisTemplate.opsForValue().set(USER_PROFILE_CACHE_PREFIX+username,userDto, CACHE_EXIST_TIME);
    }

    private void copyUserProperties(User user, UserDto userDto) {
        int roleId = user.getRoleId()-1;
        String roleName = roleNames.get(roleId);
        BeanUtils.copyProperties(user,userDto);
        userDto.setRole(roleName);
    }

    private List<UserDto> copyUsers(Iterator<User> users){
        List<UserDto> userDtos = new ArrayList<>();
        User user;
        while (users.hasNext()){
            user = users.next();
            UserDto userDto = new UserDto();
            copyUserProperties(user,userDto);
            userDtos.add(userDto);
        }
        return userDtos;
    }
}
