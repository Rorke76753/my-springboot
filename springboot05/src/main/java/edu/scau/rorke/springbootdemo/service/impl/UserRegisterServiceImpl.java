package edu.scau.rorke.springbootdemo.service.impl;

import edu.scau.rorke.springbootdemo.dao.UserDao;
import edu.scau.rorke.springbootdemo.entity.User;
import edu.scau.rorke.springbootdemo.service.UserRegisterService;
import edu.scau.rorke.springbootdemo.utils.PasswordEncodingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Rorke
 * @date 2020/2/28 21:31
 */
@Service
public class UserRegisterServiceImpl implements UserRegisterService {
    private UserDao userDao;
    @Autowired
    public UserRegisterServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public User getUserByNickname(String nickname) {
        return userDao.findByNickName(nickname);
    }

    @Override
    public User saveUser(String userName, String nickName, String password) {
        User user = new User();
        user.setUserName(userName);
        user.setNickName(nickName);
        user.setRoleId(3);
        user.setSalt();
        user.setPassword(PasswordEncodingUtil.saltedPassword(user.getSalt(),password));
        return userDao.saveAndFlush(user);
    }
}
