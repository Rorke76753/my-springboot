package edu.rorke.demo.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Rorke
 * @date 2020/2/18 17:30
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {
    @Autowired
    @Bean
    public HelloService helloService(HelloProperties helloProperties) {
        HelloService service = new HelloService();
        service.setHelloProperties(helloProperties);
        return service;
    }
}
