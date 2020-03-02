package edu.scau.rorke.springbootdemo.service.impl;

import edu.scau.rorke.springbootdemo.dao.RoleDao;
import edu.scau.rorke.springbootdemo.dao.UserDao;
import edu.scau.rorke.springbootdemo.entity.User;
import edu.scau.rorke.springbootdemo.service.SimpleRoleService;
import edu.scau.rorke.springbootdemo.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 专门提供给登录功能使用的service
 * @author Rorke
 * @date 2020/2/28 15:13
 */
@Service
public class UserLoginServiceImpl extends SimpleRoleService implements UserLoginService {
    private final UserDao userDao;

    @Autowired
    public UserLoginServiceImpl(UserDao userDao, RoleDao roleDao) {
        super(roleDao);
        this.userDao = userDao;
    }

    /**
     * 根据用户名获得用户
     * @param username  用户名
     * @return  用户
     */
    @Override
    public User getUserByUsername(String username) {
        return userDao.findByUserName(username);
    }
}
