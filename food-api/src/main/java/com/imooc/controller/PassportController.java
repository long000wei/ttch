package com.imooc.controller;

import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 龙伟
 * @Description:
 * @date 2020/12/13 20:54
 */
@Api(value = "注册登录",tags = {"用于注册登录的相关接口"})
@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username){

        //1. 判断用户名不能为空
        if(StringUtils.isBlank(username)) return IMOOCJSONResult.errorMsg("用户名不能为空");

        //2.用户名重复
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) return IMOOCJSONResult.errorMsg("用户名已存在");

        //3.用户名可用
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户注册",notes = "用户注册呀",httpMethod = "POST")
    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBO userBO){

        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        //0.判断用户名是否存在
        if (StringUtils.isBlank(username) ||
            StringUtils.isBlank(password) ||
            StringUtils.isBlank(confirmPwd)) return IMOOCJSONResult.errorMsg("用户名或密码不能位空");

        //1.判断用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) return IMOOCJSONResult.errorMsg("用户名已经存在");

        //2.密码长度不能小于6位
        if (password.length() < 6) return IMOOCJSONResult.errorMsg("密码长度不能小于6");

        //3.判断两次密码是否一致
        if (!password.equals(confirmPwd)) return IMOOCJSONResult.errorMsg("两次密码不一致");

        //4.实现注册
        userService.createUser(userBO);

        return IMOOCJSONResult.ok();
    }




}
