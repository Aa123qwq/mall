package com.zzt.mall.controller;

import com.zzt.mall.common.CommonResult;
import com.zzt.mall.entity.UserInfo;
import com.zzt.mall.service.UserInfoService;
import com.zzt.mall.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 登录
     */
    @GetMapping("/login")
    public CommonResult Login(String userName,String password){
        UserInfo userInfo1=userInfoService.getByUserName(userName);
        if(userInfo1==null)
            return new CommonResult(404,"用户不存在");
        UserInfo userInfo2=userInfoService.getByUserNameAndPassword(userName,password);
        if(userInfo2==null)
            return new CommonResult(401,"账号或密码错误");
        String token= JWTUtil.sign(userName,password);
        return new CommonResult(200,"登录成功",token);
    }

    /**
     * 注册
     * @param userInfo 用户信息
     * @return
     */
    @PostMapping("/register")
    public CommonResult register(@RequestBody UserInfo userInfo){
        UserInfo userInfo1=userInfoService.getByUserName(userInfo.getUserName());
        if(userInfo1!=null)
            return new CommonResult(403,"用户名重复");
        userInfoService.register(userInfo);
        return new CommonResult(200,"注册成功");
    }
}
