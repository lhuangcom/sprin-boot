package com.neo.Realms;


import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by asus on 2018/8/4.
 */
public class MyRealm2 implements Realm {


    public String getName() {
        return "myrealm2";
    }


    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken; //仅支持UsernamePasswordToken类型的Token

    }


    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String)token.getPrincipal();  //得到用户名
        String password = new String((char[])token.getCredentials()); //得到密码
        if(!"wang".equals(username)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"123".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
