package com.score.dao.impl;

import com.score.dao.ITeacherCourseDao;
import com.score.util.JDBCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherCourseDaoImpl implements ITeacherCourseDao {

    private static final Logger logger = LoggerFactory.getLogger(TeacherCourseDaoImpl.class);

    @Override
    public List<Long> findCourseIdByTeacherId(Long teacherId) {
        String sql = "select ctID from teacher_course where tcID = ?";
        List<Long> courseIds = new ArrayList<>();
        try(Connection conn = JDBCUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1,teacherId);
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    courseIds.add(rs.getLong("ctID"));
                }
            }
            logger.info("查询老师任课成功，老师ID:{},课程数：{}",teacherId,courseIds.size());
        }catch (SQLException e){
            logger.error("查询老师任课失败，老师ID：{}",teacherId,e);
        }
        return courseIds;
    }
}
