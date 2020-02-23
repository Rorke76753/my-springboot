package edu.scau.rorke.springbootdemo.controller;

import edu.scau.rorke.springbootdemo.dao.UserDao;
import edu.scau.rorke.springbootdemo.utils.PasswordEncodingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author Rorke
 * @date 2020/2/12 16:26
 */
@Controller
public class LoginController {
    private UserDao userDao;
    private PasswordEncodingUtil passwordUtil;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public LoginController(UserDao userDao, PasswordEncodingUtil passwordUtil) {
        this.userDao = userDao;
        this.passwordUtil = passwordUtil;
    }

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String userName,
                        @RequestParam("password") String password,
                        Map<String, Object> map) {
        if (userDao.findByUserName(userName) == null
                || !userDao.findByUserName(userName).getPassword().equals(passwordUtil.passwordMD5Encoding(password))) {
            map.put("login_fail_msg",
                    "用户名或密码错误"
                    +"\n"
                    +"UserName or password is error");
            return "login";
        } else {
            return "dashboard";
        }
    }
}
