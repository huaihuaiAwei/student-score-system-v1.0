package com.score.service.impl;

import com.score.dao.IStudentDao;
import com.score.dao.impl.StudentDaoImpl;
import com.score.pojo.Student;
import com.score.service.IStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentServiceImpl implements IStudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    //Service持有Dao层的引用，面向接口
    private final IStudentDao studentDao = new StudentDaoImpl();

    @Override
    public void addStudent(Student student) throws Exception {
        //1.先根据ID查询，此学号是否存在
        Student existing = studentDao.findById((long) student.getId());
        if(existing != null){
            logger.warn("添加学生失败，学生{}已存在",student.getId());
            //2.如果存在，直接抛出业务异常
            throw new Exception("学号"+student.getId()+"已存在，添加失败！");
        }
        //3.如果不存在，调用Dao层执行插入
        int result = studentDao.insert(student);
        if(result>0){
            logger.info("添加学生成功，学号：{}",student.getId());
        }else{
            //理论上走不到此处，保证逻辑完整
            throw new Exception("添加学生失败，数据库未知错误");
        }
    }

    @Override
    public void deleteStudent(Long id) throws Exception {
        //1.先查询此学生是否存在
        Student existing = studentDao.findById(id);
        if(existing == null){
            logger.warn("删除学生失败，学号：{}不存在。",id);
            throw new Exception("学号"+ id +"不存在，删除失败！");
        }

        //2.存在则执行删除
        int result = studentDao.deleteById(id);
        if(result>0) {
            logger.info("删除学生成功，学号：{}", id);
        }else{
            throw new Exception("删除学生失败，发生未知数据库错误！");
        }


    }

    @Override
    public void updateStudent(Student student) throws Exception {
        //1.先查询此学生是否存在
        Student existing = studentDao.findById((long) student.getId());
        if(existing == null){
            logger.warn("修改学生信息失败，学号：{}不存在",student.getId());
            throw new Exception("学号" + student.getId() + "不存在,删除失败" );
        }

        //2.执行更新
        int result = studentDao.update(student);
        if(result>0){
            logger.info("修改学生成功，学号：{}",student.getId());
        }else{
            throw new Exception("修改学生失败，数据库未知错误");
        }
    }

    @Override
    public Student getStudentById(Long id) throws Exception {
        Student student = studentDao.findById(id);
        if(student == null){
            logger.warn("查询学生失败，学号{}不存在！",id);
            throw new Exception("学号" + id + "不存在！");
        }
        return student;

    }
}
