package com.score.service.impl;

import com.score.dao.IScoreDao;
import com.score.dao.IStudentDao;
import com.score.dao.impl.ScoreDaoImpl;
import com.score.dao.impl.StudentDaoImpl;
import com.score.pojo.Score;
import com.score.service.IScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ScoreServiceImpl implements IScoreService {

    private static final Logger logger = LoggerFactory.getLogger(ScoreServiceImpl.class);
    private final IScoreDao scoreDao = new ScoreDaoImpl();
    private final IStudentDao studentDao = new StudentDaoImpl();

    @Override
    public void addScore(Score score) throws Exception {
        //1.首先检查学生是否存在
        if(studentDao.findById((long) score.getStudentId()) == null){
            throw new Exception("学号" + score.getStudentId() + "不存在，无法录入成绩！");
        }
        //2.检查是否已有成绩
        List<Score> existing = scoreDao.findByStudentId((long) score.getStudentId());
        for (Score s : existing) {
            if(s.getCourseId() == score.getCourseId()){
                throw new Exception("该学生此课程已有成绩，请使用修改功能");
            }
        }

        //3.执行插入
        int result = scoreDao.insertOrUpdate(score);
        if(result <= 0){
            throw new Exception("学生成绩录入失败，请检查数据库状态");
        }
        logger.info("成绩录入成功，学生ID:{},课程ID:{}",score.getStudentId(),score.getCourseId());
    }

    @Override
    public void deleteScore(Long studentId, Long courseId) throws Exception {
        //1.校验成绩的存在性
        List<Score> list = scoreDao.findByStudentId(studentId);
        boolean exists = list.stream().anyMatch(score -> score.getCourseId() == courseId);
        if(!exists){
            throw new Exception("该成绩不存在，删除失败！");
        }
        int result = scoreDao.deleteByStudentAndScore(studentId,courseId);
        if(result <= 0){
            throw new Exception("删除成绩失败");
        }
        logger.info("删除成绩成功，学生ID:{},课程ID:{}",studentId,courseId);
    }

    @Override
    public void updateScore(Score score) throws Exception {
        List<Score> list = scoreDao.findByStudentId((long) score.getStudentId());
        boolean exists = list.stream().anyMatch(s -> s.getCourseId() == score.getCourseId());
        if(!exists){
            throw new Exception("该学生此课程无成绩，无法修改，请先录入。");
        }
        logger.info("成绩修改成功，学生ID:{},课程ID:{}",score.getStudentId(),score.getCourseId());
    }

    @Override
    public List<Score> getScoreByStudent(Long studentId) throws Exception {
        List<Score> list = scoreDao.findByStudentId(studentId);
        if(list.isEmpty()){
            throw new Exception("该学生暂无成绩查询");
        }

        return list;
    }
}
