package com.redis.util.redis_util;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

/**
 * @author hengtao.wu
 * @Date 2020/9/27 16:08
 **/
@Configuration
public class RedisCondig {


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //注入连接工厂
        template.setConnectionFactory(redisConnectionFactory);
        //创建序列化类：实现了RedisSerializer接口的类都可以使用
        Jackson2JsonRedisSerializer<Object> fastJsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        fastJsonRedisSerializer.setObjectMapper(om);

        //创建string类型的序列化对象
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        //key使用string的序列化
        template.setKeySerializer(stringRedisSerializer);
        //value使用json的序列化
        template.setValueSerializer(fastJsonRedisSerializer);
        //key使用string的序列化
        template.setHashKeySerializer(stringRedisSerializer);
        //value使用json的序列化
        template.setHashValueSerializer(fastJsonRedisSerializer);
        //使参数生效
        template.afterPropertiesSet();
        return template;
    }
}
