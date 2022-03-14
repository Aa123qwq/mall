package com.zzt.mall.service;

import com.zzt.mall.entity.UserInfo;
import com.zzt.mall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserMapper userMapper;

    public UserInfo getByUserNameAndPassword(String userName,String password){
        UserInfo userInfo=userMapper.getByUserNameAndPassword(userName,password);
        return userInfo;
    }

    public UserInfo getByUserName(String username){
        return userMapper.getByUserName(username);
    }

    public int register(UserInfo userInfo){
        return userMapper.register(userInfo);
    }
}
