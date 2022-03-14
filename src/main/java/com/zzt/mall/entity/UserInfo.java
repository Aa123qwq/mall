package com.zzt.mall.entity;

import lombok.Data;

@Data
public class UserInfo {

    private int userId;
    private String userName;
    private String password;
    private int status;
    private String email;
}
