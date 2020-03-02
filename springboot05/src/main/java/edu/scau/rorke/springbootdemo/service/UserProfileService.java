package edu.scau.rorke.springbootdemo.service;

import edu.scau.rorke.springbootdemo.entity.dto.UserDto;

import java.util.List;

public interface UserProfileService {
    String USER_PROFILE_CACHE_PREFIX="user_profile_";

    UserDto getUserProfileByUsername(String username);

    List<UserDto> getAllUser(int page, int size);

    List<UserDto> getAllUser();

    void saveUserProfile(String username, String nickname, String password);
}
