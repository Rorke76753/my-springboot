package edu.scau.rorke.springbootdemo.service;

import edu.scau.rorke.springbootdemo.entity.User;

public interface UserLoginService{
    User getUserByUsername(String username);
}
