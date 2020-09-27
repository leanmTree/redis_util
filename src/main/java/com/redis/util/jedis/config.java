package com.redis.util.jedis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

/**
 * @author hengtao.wu
 * @Date 2020/9/27 14:03
 **/
@Configuration
public class config {

   /* @Bean
    public Jedis jedis() {
        JedisShardInfo info = new JedisShardInfo("124.71.112.168", 6379);
        info.setPassword("root");
        Jedis jedis = new Jedis(info);
        System.out.println(jedis.ping());
        return jedis;
    }*/
}
