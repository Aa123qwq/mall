package com.zzt.mall.controller;

import com.zzt.mall.common.CommonResult;
import com.zzt.mall.entity.UserInfo;
import com.zzt.mall.service.UserInfoService;
import com.zzt.mall.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 登录
     */
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

    public CommonResult register(String username,String  password){
        return new CommonResult(200,"注册成功");
    }
}
