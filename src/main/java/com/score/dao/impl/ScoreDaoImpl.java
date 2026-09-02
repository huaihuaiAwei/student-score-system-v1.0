package com.score.dao.impl;

import com.score.dao.IScoreDao;
import com.score.pojo.Score;
import com.score.util.JDBCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreDaoImpl implements IScoreDao {

    private static final Logger logger = LoggerFactory.getLogger(ScoreDaoImpl.class);

    @Override
    public int insertOrUpdate(Score score) {

        String sql = "insert into score (SID,CID,score) values (?,?,?)" +
                "on duplicate key update score = values(score)";
        try(Connection conn = JDBCUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1,score.getStudentId());
            pstmt.setLong(2,score.getCourseId());
            pstmt.setInt(3,score.getScore());
            int result = pstmt.executeUpdate();
            logger.info("成绩录入成功，学号：{} ，课程：{} ， 影响行数：{}",score.getStudentId()
            ,score.getCourseId(),result);
            return result;
        }catch(SQLException e){
            logger.error("成绩录入/更新失败，学号：{}，课程：{}",score.getStudentId(),score.getCourseId());
            return 0;
        }
    }

    @Override
    public int deleteByStudentAndScore(Long studentId, Long courseId) {
        String sql = "delete from score where SID = ? and CID = ?";
        try(Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1,studentId);
            pstmt.setLong(2,courseId);
            int result = pstmt.executeUpdate();
            logger.info("删除成绩成功，学号：{}，课程：{},影响行数：{}",studentId,courseId,result);
            return result;
        }catch(SQLException e){
            logger.error("删除成绩成功，学号：{}，课程：{}",studentId,courseId);
            return 0;
        }
    }

    @Override
    public List<Score> findByStudentId(Long studentId) {
        String sql = "select SID , CID, score from score where SID = ?";
        List<Score> list = new ArrayList<>();
        try(Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1,studentId);
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    Score score = new Score();
                    score.setStudentId((int) rs.getLong("SID"));
                    score.setCourseId((int) rs.getLong("CID"));
                    score.setScore(rs.getInt("score"));
                    list.add(score);
                }
            }
        }catch(SQLException e){
            logger.error("查询学生成绩失败，学号：{}",studentId,e);
        }
        return list;
    }

    @Override
    public List<Score> findByCourseId(Long courseId) {
        String sql = "select SID ,CID ,score from score where CID = ? order by score desc";
        List<Score> list = new ArrayList<>();
        try(Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1,courseId);
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    Score score = new Score();
                    score.setStudentId((int) rs.getLong("SID"));
                    score.setCourseId((int) rs.getLong("CID"));
                    score.setScore(rs.getInt("score"));
                    list.add(score);
                }
            }
        }catch (SQLException e){
            logger.error("查询课程成绩失败，课程ID：{}",courseId,e);
        }
        return list;
    }
}
