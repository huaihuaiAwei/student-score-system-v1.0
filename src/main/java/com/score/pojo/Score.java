package com.score.pojo;

public class Score {
    int studentId;
    int courseId;
    int score ;


    public Score() {
    }

    public Score(int studentId, int courseId, int score) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.score = score;
    }

    /**
     * 获取
     * @return studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * 设置
     * @param studentId
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * 获取
     * @return courseId
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * 设置
     * @param courseId
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    /**
     * 获取
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * 设置
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    public String toString() {
        return "Score{studentId = " + studentId + ", courseId = " + courseId + ", score = " + score + "}";
    }
}
