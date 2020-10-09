package com.redis.util;

import com.redis.util.pojo.User;
import com.redis.util.redis_util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class UtilApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        User u1 = new User("张三", 1);
        User u2 = new User("李四", 2);
        User u3 = new User("王五", 3);
        List<User> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        list.add(u3);
//        redisTemplate.opsForList().leftPushAll("list", list);
        Map<String, Object> map = new HashMap<>();
        map.put("name","lisi");
        map.put("age", 56);
        redisTemplate.opsForHash().put("user", "id:3", u3);
        redisTemplate.opsForHash().put("user:1001", "name", "zhangsan");
        redisTemplate.opsForHash().put("user:1001", "age", "55");
        System.out.println((User)redisTemplate.opsForHash().get("user", "id:3"));
        System.out.println(redisTemplate.opsForHash().entries("user:1001"));
        System.out.println(redisTemplate.opsForHash().values("user:1001"));

    }

}
