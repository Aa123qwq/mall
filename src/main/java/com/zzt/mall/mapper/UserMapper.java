package com.zzt.mall.mapper;

import com.zzt.mall.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    UserInfo getByUsernameAndPassword(String userName,String password);

    UserInfo getByUserName(String userName);
}
