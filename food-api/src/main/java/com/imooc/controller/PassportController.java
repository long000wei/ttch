package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public IMOOCJSONResult regist(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response){

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
        Users result = userService.createUser(userBO);

        result = setNullProperty(result);

        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(result),true);

        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户登录",notes = "用户登录",httpMethod = "POST")
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = userBO.getUsername();
        String password = userBO.getPassword();

        //0.判断用户名和密码不能为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password))
            return IMOOCJSONResult.errorMsg("用户名或密码不能位空");

        //.实现注册
        Users result = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));

        if (result == null) return IMOOCJSONResult.errorMsg("用户名或密码不正确");

        result = setNullProperty(result);

        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(result),true);

        return IMOOCJSONResult.ok(result);
    }

    //相关属性设置为空
    private Users setNullProperty(Users users){

        users.setMobile(null);
        users.setPassword(null);
        users.setEmail(null);
        users.setCreatedTime(null);
        users.setUpdatedTime(null);
        users.setBirthday(null);
        return users;
    }




}
