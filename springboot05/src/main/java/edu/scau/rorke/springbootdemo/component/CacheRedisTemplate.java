package edu.scau.rorke.springbootdemo.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;

/**
 * @author Rorke
 * @date 2020/2/27 15:39
 */
@Configuration
public class CacheRedisTemplate {
    /**
     * 创建自己的redisTemplate，在ioc容器中如果没有名为 redisTemplate 的bean
     * 那么就是用默认的redisTemplate<Object,Object>，而这个默认的序列化器是使用{@link JdkSerializationRedisSerializer}
     * 一般而言，在一个前后端分离的项目中使用json来进行数据传输，
     * 所以我们在这里也使用json的序列化器来实现我们的redisTemplate
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory){
        RedisTemplate<String,Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());
        return redisTemplate;
    }
}
