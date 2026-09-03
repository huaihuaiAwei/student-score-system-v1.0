package com.score.dao.impl;

import com.score.dao.ICourseDao;
import com.score.pojo.Course;
import com.score.util.JDBCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements ICourseDao {

    private static final Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);

    @Override
    public int insert(Course course) {
        String sql = "insert into course values (?,?)";
        try(Connection conn = JDBCUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1,course.getId());
            pstmt.setString(2,course.getName());
            int result = pstmt.executeUpdate();
            logger.info("课程录入成功，课程ID：{} ，课程名称：{},影响行数：{}"
                    ,course.getId(),course.getName(),result);
            return result;
        }catch(SQLException e){
            logger.error("成绩录入/更新失败，课程ID：{}，课程名称：{}",course.getId(),course.getName());
            return 0;
        }
    }

    @Override
    public List<Course> findAll() {
        String sql = "select id,name from course ";
        List<Course> list = new ArrayList<>();
        try(Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()){
            while(rs.next()){
                Course course = new Course();
                course.setId((int) rs.getLong("id"));
                course.setName(rs.getString("name"));
                list.add(course);
            }
        }catch (SQLException e){
            logger.error("查询所有课程失败",e);
            throw new RuntimeException("查询课程列表失败",e);
        }
        return list;
    }

    @Override
    public Course findById(Long id) {
        String sql = "select id, name from course where id = ? ";
        try(Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1,id);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    Course course = new Course();
                    course.setId((int) rs.getLong("id"));
                    course.setName(rs.getString("name"));
                    return course;
                }
            }
        }catch (SQLException e){
            logger.error("查询课程失败，ID：{}",id,e);
            throw new RuntimeException("查询课程失败",e);
        }
        return null;
    }
}
