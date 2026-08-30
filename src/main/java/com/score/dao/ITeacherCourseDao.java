package com.score.dao;

import java.util.List;

/*
* 定义查询老师权限
* */
public interface ITeacherCourseDao {
    //根据老师Id查询所教课程Id
    List<Long> findCourseIdByTeacherId(Long teacherId);
}
