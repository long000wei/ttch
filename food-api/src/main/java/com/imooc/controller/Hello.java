package com.imooc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 龙伟
 * @Description:
 * @date 2020/12/13 17:43
 */
@RestController
public class Hello {

    @GetMapping("/hello/{msg}")
    public String hello(@PathVariable("msg") String msg){
        return msg;
    }
}
