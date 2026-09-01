package com.score.dao.impl;

import com.score.dao.IUserDao;
import com.score.pojo.User;
import com.score.util.JDBCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements IUserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql = "select id , username , password , role from user where username = ? and password = ?";
        try(Connection conn = JDBCUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    User user = new User();
                    user.setId((int) rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    logger.info("用户{}登录查询成功",username);
                    return user;
                }
            }
        }catch(SQLException e){
            logger.error("用户登录失败，账号：{}",username,e);
        }
        return null;
    }
}
