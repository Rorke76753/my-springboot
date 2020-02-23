package edu.scau.rorke.springbootdemo.controller;

import edu.scau.rorke.springbootdemo.dao.UserDao;
import edu.scau.rorke.springbootdemo.entity.User;
import edu.scau.rorke.springbootdemo.utils.PasswordEncodingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Rorke
 * @date 2020/2/12 16:26
 */
@Controller
public class LoginController {
    private UserDao userDao;
    private PasswordEncodingUtil passwordUtil;

    public LoginController(UserDao userDao, PasswordEncodingUtil passwordUtil) {
        this.userDao = userDao;
        this.passwordUtil = passwordUtil;
    }

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String userName,
                        @RequestParam("password") String password,
                        Map<String, Object> map,
                        HttpSession session) {
        User user = userDao.findByUserName(userName);
        if ( user == null
                || !user.getPassword().equals(passwordUtil.passwordMD5Encoding(password))) {
            //登录失败，返回错误信息
            map.put("login_fail_msg",
                    "用户名或密码错误 UserName or password is error");
            return "login";
        } else {
            //登录成功，将用户添加到session中
            //通过重定向防止表单被重复提交
            session.setAttribute("loginUser",user);
            return "redirect:/main.html";
        }
    }
}
