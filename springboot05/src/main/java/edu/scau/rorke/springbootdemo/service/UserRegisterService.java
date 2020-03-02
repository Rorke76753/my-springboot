package edu.scau.rorke.springbootdemo.service;

import edu.scau.rorke.springbootdemo.entity.User;

public interface UserRegisterService extends UserLoginService {
    User getUserByNickname(String nickname);

    User saveUser(String userName, String nickName, String password);
}
