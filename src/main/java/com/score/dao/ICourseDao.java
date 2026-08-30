package com.score.dao;

import com.score.pojo.Course;

import java.util.List;

/*
 * 定义管理班级的接口
 * */

public interface ICourseDao {
    //添加课程信息
    int insert(Course course) ;

    //查询所有课程
    List<Course> findAll() ;

    //根据课程Id查询某课程
    Course findById(Long id) ;
}
