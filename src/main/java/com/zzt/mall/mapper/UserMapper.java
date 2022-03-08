package com.zzt.mall.mapper;

import com.zzt.mall.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserInfo getByUsernameAndPassword(UserInfo userInfo);
}
