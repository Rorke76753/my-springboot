package edu.scau.rorke.springbootdemo.controller;

import edu.scau.rorke.springbootdemo.dao.RoleDao;
import edu.scau.rorke.springbootdemo.dao.UserDao;
import edu.scau.rorke.springbootdemo.entity.Role;
import edu.scau.rorke.springbootdemo.entity.User;
import edu.scau.rorke.springbootdemo.utils.PasswordEncodingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rorke
 * @date 2020/2/13 16:26
 */
@Controller
public class UserController{
    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncodingUtil passwordEncodingUtil;
    private List<User> userList = new ArrayList<>();
    private List<Role> roleList;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserController(UserDao userDao, RoleDao roleDao, PasswordEncodingUtil passwordEncodingUtil) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncodingUtil = passwordEncodingUtil;
        roleList = roleDao.findAll();
    }

    //每个用户都能看到所有的用户
    //对应查询所有用户的操作
    //TODO:还需要再修改这一个方法，达到我想要的功能
    @GetMapping(value = "/users")
    public String list(Model model, HttpSession session) {
        getUserList(session);
        model.addAttribute("roleList", roleList);
        model.addAttribute("userList", userList);
        return "users/list";
    }

    //用户查看自己个人资料
    //点击编辑之后跳到这里
    @GetMapping(value = "/users/profile/{username}")
    public String userProfile(@PathVariable("username") String username
                                ,Model model) {
        User user = userDao.findByUserName(username);
        model.addAttribute("profileUser",user);
        return "users/profile";
    }

    //修改完成后
    @PutMapping(value = "/users/profile/{username}")
    public String saveUserProfile(@PathVariable("username") String username,
                                  @RequestParam("nickName") String nickName,
                                  @RequestParam("password") String password){
        User user = userDao.findByUserName(username);
        user.setNickName(nickName);
        if(!password.isEmpty()){
            user.setPassword(passwordEncodingUtil.passwordMD5Encoding(password));
        }
        userDao.saveAndFlush(user);
        return "redirect:/users";
    }

    @DeleteMapping(value = "/users/{username}")
    public String deleteUser(@PathVariable("username")String username){
        logger.info("delete");
        User user = userDao.findByUserName(username);
        userDao.delete(user);
        return "redirect:/users";
    }

    //根据不同的用户权限获取相对应的用户列表
    private void getUserList(HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        switch (user.getRoleId()){
            case 3: userList.clear();
                userList.add(userDao.findByUserName(user.getUserName()));
                break;
            default: userList = userDao.findAll();
        }
    }
}
