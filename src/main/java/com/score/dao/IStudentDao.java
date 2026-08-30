package com.score.dao;

import com.score.pojo.Student;

import java.util.List;

/*
 * 定义学生档案的访问接口
 */
public interface IStudentDao {

    //添加一个学生到数据库
    int insert(Student student) ;

    //根据学生ID删除学生
    int deleteById(Long id) ;

    //修改学生信息
    int update(Student student) ;

    //根据学号ID查询单个学生
    Student findById(Long id) ;

    //查询所有学生信息
    List<Student> findAll() ;

}
