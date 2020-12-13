package com.imooc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author 龙伟
 * @Description:
 * @date 2020/12/13 17:43
 */
@ApiIgnore
@RestController
public class Hello {

    @GetMapping("/hello/{msg}")
    public String hello(@PathVariable("msg") String msg){
        return msg;
    }
}
