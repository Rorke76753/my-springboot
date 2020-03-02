package edu.scau.rorke.springbootdemo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
public class LoginController extends SimpleController {
    /**
     * 使用shiro重写登录的功能
     * @param userName 用户名
     * @param password 密码
     * @param map      存放登录状态
     * @param session  目前存放在服务器中
     * @return         登陆成功则重定向到后台首页，登陆失败则返回登录界面
     */
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String userName,
                        @RequestParam("password") String password,
                        Map<String, String> map,
                        HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
         try {
            subject.login(token);
            session.setAttribute("loginUser",userName);
            return "redirect:/main.html";
        }catch (Exception e){
            map.clear();
            map.put("login_fail_msg",e.getMessage());
            return "login";
        }
    }

}
