package com.score.dao;

import com.score.pojo.User;

public interface IUserDao {
    User findByUsernameAndPassword(String username, String password);
}
