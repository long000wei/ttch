package com.imooc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
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

    public static void main(String[] args) {

        Jedis jedis = new Jedis("192.168.220.135",6379);
        String pong = jedis.ping();
        System.out.println(pong);
    }


}
