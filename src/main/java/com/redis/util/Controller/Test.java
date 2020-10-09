package com.redis.util.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hengtao.wu
 * @Date 2020/9/29 11:59
 **/
@RestController
public class Test {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test () {
        return "hello，world";
    }
}
