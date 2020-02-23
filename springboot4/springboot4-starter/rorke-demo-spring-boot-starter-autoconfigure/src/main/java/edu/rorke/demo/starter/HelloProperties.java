package edu.rorke.demo.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Rorke
 * @date 2020/2/18 17:26
 */
@Component
@ConfigurationProperties(prefix = "edu.rorke")
public class HelloProperties {
    private String userName;
    private String welcome;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }
}
