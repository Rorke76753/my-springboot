package edu.rorke.demo.starter;

import java.util.Date;

/**
 * @author Rorke
 * @date 2020/2/18 17:25
 */
public class HelloService {
    private HelloProperties helloProperties;

    public HelloService() {
    }

    public String hello(Date date) {
        return date.toString() + ":"
                + helloProperties.getUserName()+" "
                + helloProperties.getWelcome();
    }

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }
}
