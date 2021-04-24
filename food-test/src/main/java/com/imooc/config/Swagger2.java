package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 龙伟
 * @Description:
 * @date 2021/4/21 23:13
 */

@Configuration
@EnableSwagger2
public class Swagger2 {

    // 配置swagger2 核心配置   Docket
    @Bean
    public Docket createRestApi(){

        return new Docket(DocumentationType.SWAGGER_2)    //指定aip类型为swagger2
                            .apiInfo(apiInfo())                      // 用于定义api文档汇总信息
                            .select()
                            .apis(RequestHandlerSelectors.basePackage("com.imooc.controller"))  //指定controller 包
                            .paths(PathSelectors.any())                                        //所有controller 下的path
                            .build();


    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("测试平台 接口api")                                     //文档标题
                .contact(new Contact("网站名","http://www.com","abc@163.com"))  //联系人信息
                .description("提供新人api文档")   //详细信息
                .version("1.0.1")               //文档版本号
                .termsOfServiceUrl("http://www.com")   //网站地址
                .build();
    }

    //访问 1：           http
    //访问 2：           http



}
