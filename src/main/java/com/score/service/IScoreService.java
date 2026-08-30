package com.score.service;

import com.score.pojo.Score;

import java.util.List;

/*
* 成绩管理的业务逻辑接口
* */
public interface IScoreService {
    /*
    * 录入学生成绩（若该学生已有成绩，不能覆盖，提示“已存在”）
    * */
    void addScore(Score score) throws Exception ;

    /*
    * 删除学生某科成绩（若该学生无成绩，则提示不存在）
    * */
    void deleteScore(Long studentId, Long courseId) throws Exception ;

    /*
    * 修改成绩（若学生无成绩，则提示“不存在，无法修改”）
    * */
    void updateScore(Score score) throws Exception ;

    /*
    * 查询学生所有成绩
    * */
    List<Score> getScoreByStudent(Long studentId) throws Exception ;
}
