package edu.scau.rorke.springbootdemo.controller;

import edu.scau.rorke.springbootdemo.dao.UserDao;
import edu.scau.rorke.springbootdemo.entity.User;
import edu.scau.rorke.springbootdemo.utils.PasswordEncodingUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author Rorke
 * @date 2020/2/12 17:09
 */
@Controller
public class RegisterController {
    private UserDao userDao;
    private PasswordEncodingUtil passwordUtil;

    public RegisterController(UserDao userDao,PasswordEncodingUtil passwordUtil) {
        this.userDao = userDao;
        this.passwordUtil = passwordUtil;
    }

    @PostMapping(value = "/user/register")
    public String register(@RequestParam("username")String userName,
                           @RequestParam("nickname")String nickName,
                           @RequestParam("password")String password,
                           Map<String,Object> map){
        if(userDao.findByUserName(userName)==null
                &&userDao.findByNickName(nickName)==null){
            User user = new User();
            user.setRoleId(3);
            user.setNickName(nickName);
            user.setUserName(userName);
            user.setPassword(passwordUtil.passwordMD5Encoding(password));
            userDao.saveAndFlush(user);
            return "login";
        }else {
            map.put("registry_fail_msg","已有重复的用户名或用户名称 Username or nickname already exists");
            return "register";
        }
    }
}
