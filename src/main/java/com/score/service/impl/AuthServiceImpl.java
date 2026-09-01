package com.score.service.impl;

import com.score.dao.IUserDao;
import com.score.dao.impl.UserDaoImpl;
import com.score.pojo.User;
import com.score.service.IAuthService;

public class AuthServiceImpl implements IAuthService {

    private final IUserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) throws Exception {
        //1.基本空值校验
        if(username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()){
            throw new Exception("用户名或密码不能为空！");
        }

        //2.调用Dao查询数据库
        User user = userDao.findByUsernameAndPassword(username,password);
        if(user == null){
            throw new Exception("用户名或密码错误，请重新输入！");
        }

        //3.登录成功
        return user;
    }
}
