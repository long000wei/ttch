package com.imooc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class Hello {

    final static Logger logger = LoggerFactory.getLogger(Hello.class);

    @GetMapping("/hello/{msg}")
    public String hello(@PathVariable("msg") String msg){


        System.out.println(msg);
/*        logger.debug("debug:hello");
        logger.info("info:hello");
        logger.warn("warn:hello");
        logger.error("error:hello");*/
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
