package com.score.dao;

import com.score.pojo.Score;

import java.util.List;

/*
 * 定义学生成绩访问接口
 * */
public interface IScoreDao {
    //查询/修改成绩
    int insertOrUpdate(Score score);

    //删除成绩(某学生删除某课程)
    int deleteByStudentAndScore(Long studentId ,Long courseId) ;

    //查询某学生所有成绩
    List<Score> findByStudentId(Long studentId) ;

    //查询某课程所有成绩
    List<Score> findByCourseId(Long courseId) ;

}
