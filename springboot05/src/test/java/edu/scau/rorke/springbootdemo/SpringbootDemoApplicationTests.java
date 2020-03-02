package edu.scau.rorke.springbootdemo;


import edu.scau.rorke.springbootdemo.entity.User;
import edu.scau.rorke.springbootdemo.service.SimpleRoleService;
import edu.scau.rorke.springbootdemo.utils.PasswordEncodingUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class SpringbootDemoApplicationTests {
    @Test
    public void saltTest(){
        System.out.println(PasswordEncodingUtil.saltedPassword("100c7daa218f469194367739943c2f67","123456"));
    }
    @Test
    public void timeUnitTest(){
        System.out.println(TimeUnit.HOURS.toMinutes(3));
    }
}
