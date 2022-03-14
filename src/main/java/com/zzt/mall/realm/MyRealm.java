package com.zzt.mall.realm;

import com.zzt.mall.common.JwtToken;
import com.zzt.mall.entity.UserInfo;
import com.zzt.mall.service.UserInfoService;
import com.zzt.mall.utils.JWTUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


public class MyRealm extends AuthorizingRealm{

    private UserInfoService userInfoService;

    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof JwtToken;
    }

    /**
     * 身份验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        String token=(String)authenticationToken.getCredentials();
        String username= JWTUtil.getUserName(token);
        UserInfo userInfo=userInfoService.getByUserName(username);
        if(userInfo!=null){
            if(!JWTUtil.verify(token,username,userInfo.getPassword())){
                throw new IncorrectCredentialsException();
            }
            return new SimpleAuthenticationInfo(token,token,getName());
        }else
            throw new UnknownAccountException();
    }

    /**
     * 检测用户权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        String userName=JWTUtil.getUserName(principals.toString());
        UserInfo userInfo=userInfoService.getByUserName(userName);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        if(userInfo!=null){

        }
        return info;
    }
}
