package com.score.pojo;

public class Class {
    int id ;
    String className ;
    int teacher_id ;


    public Class() {
    }

    public Class(int id, String className, int teacher_id) {
        this.id = id;
        this.className = className;
        this.teacher_id = teacher_id;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return className
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置
     * @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 获取
     * @return teacher_id
     */
    public int getTeacher_id() {
        return teacher_id;
    }

    /**
     * 设置
     * @param teacher_id
     */
    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String toString() {
        return "Class{id = " + id + ", className = " + className + ", teacher_id = " + teacher_id + "}";
    }
}
