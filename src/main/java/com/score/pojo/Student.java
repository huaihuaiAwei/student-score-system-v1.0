package com.score.pojo;

public class Student {
    int id ;
    String name ;
    int classId ;

    public Student() {
    }

    public Student(int id, String name, int classId) {
        this.id = id;
        this.name = name;
        this.classId = classId;
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return classId
     */
    public int getClassId() {
        return classId;
    }

    /**
     * 设置
     * @param classId
     */
    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String toString() {
        return "Student{id = " + id + ", name = " + name + ", classId = " + classId + "}";
    }
}
