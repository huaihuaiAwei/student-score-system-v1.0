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
    // 临时测试入口：测试 findById 是否正常工作
    public static void main(String[] args) {
        StudentDaoImpl dao = new StudentDaoImpl();
        // 假设你的测试数据里，id=4 对应的是 '张小明'
        Student student = dao.findById(4L);

        if (student != null) {
            System.out.println("✅ 测试成功！查到学生：" + student.getName() + "，班级ID：" + student.getClassId());
        } else {
            System.out.println("❌ 测试失败，没查到数据或SQL报错，请检查日志！");
        }
    }
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

    @Override
    public int insert(Student student) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public int update(Student student) {
        return 0;
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>();
    }
}
