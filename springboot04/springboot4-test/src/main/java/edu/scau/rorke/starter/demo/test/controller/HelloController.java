package edu.scau.rorke.starter.demo.test.controller;

import edu.rorke.demo.starter.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Rorke
 * @date 2020/2/18 18:04
 */
@RestController
public class HelloController {
    private HelloService helloService;
    @Autowired
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }


    @GetMapping("/hello")
    public String hello(){
        return helloService.hello(new Date());
    }
}
