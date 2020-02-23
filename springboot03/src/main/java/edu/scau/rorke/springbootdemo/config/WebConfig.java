package edu.scau.rorke.springbootdemo.config;

import edu.scau.rorke.springbootdemo.component.LoginHandlerInterceptor;
import edu.scau.rorke.springbootdemo.component.MyLocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Rorke
 * @date 2020/2/12 14:39
 * 添加个人所需的webmvc功能
 * 同时不影响spring boot自动配置
 * 添加configuration，同时实现WebMvcConfigure
 * 如果想自己掌控springMvc的配置，那么就加上EnableWebMvc注解，spring boot对mvc内容的自动配置会失效
 * 因为WebMvcAutoConfiguration中有@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})，
 * 就是说在缺少WebMvcConfigurationSupport类型的Bean的时候才会生效
 * 而EnableWebMvc @Import({DelegatingWebMvcConfiguration.class})，
 * DelegatingWebMvcConfiguration继承了WebMvcConfigurationSupport
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送login请求访问，返回login视图
        registry.addViewController("register").setViewName("register");
        registry.addViewController("login").setViewName("login");
        registry.addViewController("index").setViewName("../public/index");
        registry.addViewController("user").setViewName("/users/list");
        registry.addViewController("main.html").setViewName("dashboard");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login","/register","/user/**","/webjars/**","/asserts/**","/druid/**");
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }


}
