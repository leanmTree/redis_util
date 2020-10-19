package com.redis.util.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.redis.util.redis_util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author hengtao.wu
 * @Date 2020/9/29 11:59
 **/
@RestController
public class Test {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/submitOrder", method = RequestMethod.GET)
    public String test (String id) throws InterruptedException {
        JSONObject object = JSON.parseObject("");
        JSONArray array = new JSONArray();
        array.add(object);
        Object a = new Object();
        String s = JSON.toJSONString(a);
        Object jsonObject = JSON.parseObject(s, Object.class);

        Integer num = 0;
        num = redisUtil.get("num");
        if(num > 0) {
            if(redisUtil.setnx("num--", id)) {
                num = num -1 ;
                Thread.sleep(2000);
                redisUtil.set("num", num);
                System.out.println("库存减少成功，当前库存为" + num);
                redisUtil.del("num--");
            }else {
              while (true) {
                  if(redisUtil.setnx("num--", id)) {
                      num = redisUtil.get("num");
                      num = num - 1;
                      Thread.sleep(2000);
                      redisUtil.set("num", num);
                      System.out.println("库存减少成功，当前库存为" + num);
                      redisUtil.del("num--");
                      break;
                  }
              }
            }
        }else {
            System.out.println("当前商品已经售罄!");
        }
        return "OK";
    }
}
