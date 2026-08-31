package com.score.util;
/*
* 数据库连接工具类
* 获取数据库连接，释放资源
* */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class JDBCUtil {

    private static final Logger logger = LoggerFactory.getLogger(JDBCUtil.class);

    //数据库连接信息
    private static final String URL = "jdbc:mysql://localhost:3306/score_system_v1?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "867907";

    //静态代码块，加载驱动
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            logger.info("MySQL加载成功！");
        }catch(ClassNotFoundException e){
            logger.error("MySQL驱动加载失败",e);
        }
    }

    /*
    * 获取数据库连接
    * */
    public static Connection getConnection() throws SQLException{
        Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
        logger.debug("数据库连接成功！");
        return conn;
    }

    public static void close(ResultSet rs, Statement stmt , Connection conn){
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException e){
                logger.error("关闭ResultSet失败",e);
            }
        }
        if(stmt != null){
            try{
                stmt.close();
            }catch(SQLException e){
                logger.error("关闭Statement失败",e);
            }
        }
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException e){
                logger.error("关闭Conntion失败",e);
            }
        }
    }


}
