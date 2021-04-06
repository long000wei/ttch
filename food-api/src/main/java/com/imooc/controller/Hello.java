package com.imooc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 龙伟
 * @Description:
 * @date 2020/12/13 17:43
 */
@ApiIgnore
@RestController
@RequestMapping("/test")
public class Hello {

    final static Logger logger = LoggerFactory.getLogger(Hello.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/set")
    public Object set(String key,String value) {

        redisTemplate.opsForValue().set(key,value);
        return "OK";
    }

    @GetMapping("/get")
    public Object get(String key) {

        String value = (String)redisTemplate.opsForValue().get(key);
        return value;
    }

    @GetMapping("/del")
    public Object del(String key) {

        redisTemplate.delete(key);

        return "OK";
    }

    @GetMapping("/hello/{msg}")
    public String hello(@PathVariable("msg") String msg){

        return msg;
    }

    @GetMapping("/setSession")
    public Object setSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("userInfo","new user");
        session.setMaxInactiveInterval(3600); //设置过期时间
        return "Ok";
    }
}
