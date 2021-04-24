package com.imooc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

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



}
