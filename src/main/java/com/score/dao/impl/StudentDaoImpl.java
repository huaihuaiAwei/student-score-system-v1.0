package com.score.dao.impl;

import com.score.dao.IStudentDao;
import com.score.pojo.Student;
import com.score.util.JDBCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
* 学生档案数据访问实现类
* */
public class StudentDaoImpl implements IStudentDao {

    private static final Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);

    //1.查询学生
    @Override
    public Student findById(Long id){
        //定义SQL查询语句（？是占位符，防止SQL注入）
        String sql = "select id,name,class_id from student where id = ?";

        //使用try-with-resources 自动关闭资源
        try(Connection conn = JDBCUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            //给占位符赋值
            pstmt.setLong(1,id);

            //执行查询，获取结果
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    Student student = new Student();
                    student.setId((int) rs.getLong("id"));
                    student.setName(rs.getString("name"));
                    student.setClassId((int) rs.getLong("class_Id"));

                    logger.info("查询学生成功，学号：{},姓名：{}",id,student.getName());
                    return student ;
                }else{
                    logger.warn("未找到学号为{}的学生",id);
                    return null ;
                }
            }
        }catch(SQLException e){
            logger.error("查询学生失败，学号：{} ，数据库异常",id,e);
            return null;
        }
    }

    // 2.添加学生
    @Override
    public int insert(Student student) {
        String sql = "insert into student (id,name,class_id) values (?,?,?)";
        try(Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1,student.getId());
            pstmt.setString(2,student.getName());
            pstmt.setLong(3,student.getClassId());
            int result = pstmt.executeUpdate();
            logger.info("添加学生成功，学号：{} ， 影响行数：{}",student.getId(),result);
            return result;
        }catch(SQLException e){
            logger.error("添加学生失败，学号：{}",student.getId());
            e.printStackTrace();
            return 0;
        }
    }

    //3.删除学生
    @Override
    public int deleteById(Long id) {
        String sql = "delect from student where id = ? ";
        try(Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1,id);
            int result = pstmt.executeUpdate();
            logger.info("删除学生成功，学号：{}， 影响行数 ：{}",id,result);
            return result;
        }catch(SQLException e){
            logger.error("删除学生失败，学号：{}",id,e);
            return 0;
        }
    }

    //4.修改学生
    @Override
    public int update(Student student) {
        String sql = "update student set name = ? ,class_id = ? where id = ?";
        try(Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,student.getName());
            pstmt.setLong(2,student.getClassId());
            pstmt.setLong(3,student.getId());
            int result = pstmt.executeUpdate();
            logger.info("修改学生成功，学号：{}，影响行数：{}",student.getId(),result);
            return result;
        }catch(SQLException e){
            logger.error("修改学生失败，学号：{}",student.getId());
            return 0;
        }
    }

    //5.查询所有学生
    @Override
    public List<Student> findAll() {
        String sql = "select id, name ,class_id from student";
        List<Student> list = new ArrayList<>();
        try(Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()){
            while(rs.next()){
                Student student = new Student();
                student.setId((int) rs.getLong("id"));
                student.setName(rs.getString("name"));
                student.setClassId((int) rs.getLong("class_id"));
                list.add(student);
            }
            logger.info("查询所有学生成功，共{}条记录",list.size());
        }catch(SQLException e){
            logger.error("查询所有学生失败",e);
        }
        return list;
    }
}
