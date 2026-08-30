package com.score.service;

import com.score.pojo.User;

/*
* 登录授权接口
* */
public interface IAuthService {
    /*
    * 登录验证（账号密码匹配，则返回用户对象）
    * */
    User login(String username , String password) throws Exception ;
}
