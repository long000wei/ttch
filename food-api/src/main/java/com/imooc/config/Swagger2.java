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
 * @date 2020/12/13 21:57
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    // 访问地址   http://localhost:8081/swagger-ui.html  原路径

    // 访问地址   http://localhost:8081/doc.html  路径


    //配置swagger 2 核心配置
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2) //指定aip类型为swagger 2
                    .apiInfo(apiInfo())   //定义api文档信息
                    .select().apis(RequestHandlerSelectors.basePackage("com.imooc.controller")) //指定controller包
                    .paths(PathSelectors.any()) //所有controller
                    .build();


    }

    //定义api
    private ApiInfo apiInfo(){

        return new ApiInfoBuilder()
                .title("天天吃货 接口api") //
                .contact(new Contact("imooc",
                                        "http://www.imooc.com",
                                        "abc@imooc.com"))
                .description("专为天天吃货提供的api文档")
                .version("1.0.1") //文档版本号
                .termsOfServiceUrl("https//www.imooc.com")  //网站信息
                .build();
    }
}
