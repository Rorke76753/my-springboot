package edu.scau.rorke.springbootdemo.controller;

import edu.scau.rorke.springbootdemo.entity.User;
import edu.scau.rorke.springbootdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Rorke
 * @date 2020/2/7 18:16
 */
@Controller
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 获取页面
     * @return 返回视图名字
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getPage(){
        return "users";
    }

    @RequestMapping(value = "/{user_id}",method = RequestMethod.GET)
    public String getUser(@PathVariable("user_id")int id,Model model){
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute(user);
        return "users";
    }

    @RequestMapping(value = "/{user_id}",method = RequestMethod.POST)
    public String getUser(@PathVariable("user_id") int id,@ModelAttribute User user){
        return insertUser(user);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insertUser(@ModelAttribute User user){
        int id = userRepository.save(user).getId();
        return "redirect:/users/"+ id;
    }
}
