package edu.scau.rorke.springbootdemo.controller;


import edu.scau.rorke.springbootdemo.entity.User;
import edu.scau.rorke.springbootdemo.entity.dto.UserDto;
import edu.scau.rorke.springbootdemo.exception.NickNameAlreadyExistException;
import edu.scau.rorke.springbootdemo.service.impl.UserProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Rorke
 * @date 2020/2/13 16:26
 */
@Controller
public class UserProfileController extends SimpleController {
    private UserProfileServiceImpl profileService;

    @Autowired
    public UserProfileController(UserProfileServiceImpl profileService) {
        this.profileService = profileService;
    }

    /**
     * 返回所有的用户，对应的需要权限为SUPER_ADMIN才可以访问
     * @param model spring mvc 中的model
     * @return      返回结果并跳转到list页面
     */
    @GetMapping("/users")
    public String getUsers(Model model) {
        List<UserDto> userDtos = profileService.getAllUser();
        model.addAttribute("userList", userDtos);
        return "users/list";
    }

    /**
     * 获得用户的资料
     * @param username 用户名
     * @param model    spring mvc 中的model
     * @return         返回结果并跳转到profile页面
     */
    @GetMapping("/user/profile/{username}")
    public String getUserProfile(@PathVariable("username") String username,
                                 Model model) {
        UserDto profile = profileService.getUserProfileByUsername(username);
        model.addAttribute("userProfile", profile);
        return "users/profile";
    }

    /**
     * 修改用户的资料
     * @param username 用户名，表单的数据
     * @param nickname 用户昵称，表单的数据
     * @param password 密码，表单的数据
     * @param map      存放修改资料错误信息
     * @return         修改成功返回主页面，否则返回修改页面
     */
    @PutMapping("/user/profile/{username}")
    public String saveUserProfile(@PathVariable String username,
                                  @RequestParam String nickname,
                                  @RequestParam String password,
                                  Map<String, String> map) {
        try {
        profileService.saveUserProfile(username, nickname, password);
        } catch (NickNameAlreadyExistException e) {
            map.clear();
            map.put("modify_profile_fail_msg",e.getMessage());
            return "redirect:/user/profile/{username}";
        }
        return "redirect:/main.html";
    }
}
