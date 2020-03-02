package edu.scau.rorke.springbootdemo.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Component;
/**
 * @author Rorke
 * @date 2020/2/12 18:26
 */
public class PasswordEncodingUtil {
    public static String saltedPassword(String salt,String password){
        return new Md5Hash(password,salt,6).toString();
    }
}
