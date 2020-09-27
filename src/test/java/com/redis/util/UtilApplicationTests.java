package com.redis.util;

import com.redis.util.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class UtilApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        // opsForValue: 相当于操作string类型 opsFor**就是操作某某类型的意思。后面的API几乎与命令一致
       /* redisTemplate.opsForValue().set("kkk1", "vvv1");
        redisTemplate.opsForList().leftPush("list", 1223);
        redisTemplate.opsForHash().put("user_1", "name", "张三");
        //常用的操作可以直接通过redisTemplate对象获取API
        redisTemplate.delete("");
        redisTemplate.discard();
        redisTemplate.rename("","");
        redisTemplate.expire("","");
        //获取连接对象
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushAll();
        connection.ping();*/
        User u = new User("张三", 1);
        redisTemplate.opsForValue().set("user:1", u);
        redisTemplate.opsForHash().put("user","1", u);
        System.out.println(redisTemplate.opsForHash().get("user", "1"));


    }

}
