package com.score.service;

import com.score.pojo.Student;

/*
* 定义学生管理系统的业务逻辑，实现对学生档案的业务操作
* */
public interface IStudentService {

    /*
    * 添加学生（若存在，添加失败）
    * */
    void addStudent(Student student) throws Exception;

    /*
    *  删除学生(同时删除学生表和成绩表)
    * */
    void deleteStudent(Long id) throws Exception ;

    /*
    * 修改学生信息（若学号不存在，提示）
    * */
    void updateStudent(Student student) throws Exception ;

    /*
    * 根据学号查询学生信息
    * */
    void getStudentById(Long id) throws Exception ;

}
