package edu.scau.rorke.springbootdemo.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;
/**
 * @author Rorke
 * @date 2020/2/12 18:26
 */
@Component
public class PasswordEncodingUtil {
    public String passwordMD5Encoding (String password){
        return new Md5Hash(password).toString();
    }
}
