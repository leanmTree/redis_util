package com.redis.util.jedis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Transaction;

/**
 * @author hengtao.wu
 * @Date 2020/9/27 14:05
 **/
public class Test {

    public static void main(String[] args) {
        //如果Redis开启了密码验证，需要借助JedisShardInfo设置密码
        JedisShardInfo info = new JedisShardInfo("124.71.112.168", 6379);
        info.setPassword("root");
        Jedis jedis = new Jedis(info);
        System.out.println(jedis.ping());
        jedis.flushAll();
        //jedis进行事务控制,启动事务，获取Redis的事务对象
        Transaction multi = jedis.multi();
        JSONObject u1 = new JSONObject();
        u1.put("id", 1127);
        u1.put("name", "张三");
        JSONObject u2 = new JSONObject();
        u2.put("id", 1128);
        u2.put("name", "李四");
        try {
            multi.set("user:1127", u1.toString());
            multi.set("user:1128", u1.toString());
            int i = 1/0;
            multi.exec();  //执行事务
        } catch (Exception e) {
            multi.discard(); //如果抛出异常，就回滚事务
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user:1127"));
            jedis.close();
        }
    }
}
