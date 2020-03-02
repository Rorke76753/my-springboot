package edu.scau.rorke.springbootdemo.controller;

import edu.scau.rorke.springbootdemo.entity.User;
import edu.scau.rorke.springbootdemo.service.impl.UserRegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Rorke
 * @date 2020/2/12 17:09
 */
@Controller
public class RegisterController {
    private final UserRegisterServiceImpl registerService;

    @Autowired
    public RegisterController(UserRegisterServiceImpl registerService) {
        this.registerService = registerService;
    }

    /**
     * 重构注册的表单提交
     * @param userName  用户名
     * @param nickName  用户昵称
     * @param password  密码
     * @param map       存放消息
     * @return          注册成功跳转到登录页面，不成功则停留在注册页面
     */
    @PostMapping("/user/register")
    public String userRegister(@RequestParam("username") String userName,
                               @RequestParam("nickname") String nickName,
                               @RequestParam("password") String password,
                               Map<String, Object> map){
        User usernameResult = registerService.getUserByUsername(userName);
        User nicknameResult = registerService.getUserByNickname(nickName);
        map.clear();
        if (usernameResult==null&&nicknameResult==null){
            if(registerService.saveUser(userName,nickName,password)==null){
                map.put("registry_fail_msg","注册不成功，请重试");
                return "register";
            }
            map.put("login_fail_msg","注册成功");
            return "redirect:/login";
        }else {
            if (usernameResult!=null){
                map.put("registry_fail_msg","注册不成功，用户名重复");
            }else{
                map.put("registry_fail_msg","注册不成功，用户昵称重复");
            }
            return "register";
        }
    }
}
