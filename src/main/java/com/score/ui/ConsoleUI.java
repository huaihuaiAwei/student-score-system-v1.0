package com.score.ui;

import com.score.pojo.Student;
import com.score.pojo.User;
import com.score.service.IAuthService;
import com.score.service.IStudentService;
import com.score.service.impl.AuthServiceImpl;
import com.score.service.impl.StudentServiceImpl;

import javax.swing.*;
import java.util.Scanner;

/*
* 控制台用户界面
* 作用：展示菜单，接受键盘输入，调用Service层
* */
public class ConsoleUI {

    //实现键盘输入，整个类共用
    private final Scanner sc = new Scanner(System.in) ;

    //实现Service的引用
    private final IAuthService authService = new AuthServiceImpl();
    //private final IScoreService scoreService = new scoreServiceImpl();
    private final IStudentService studentService = new StudentServiceImpl();

    //main方法
    public static void main(String[] args) {
        new ConsoleUI().start();
    }
    /*
    * 系统主入口
    * */
    public void start() {
        System.out.println("=================================");
        System.out.println("欢迎来到学生成绩管理系统 v1.0");
        System.out.println("=================================");

        //第一步：登录
        User currentUser = login();

        //如果登录失败，返回null，退出登录
        if (currentUser == null) {
            System.out.println("登录失败，请重新登录");
            return;
        }

        //第二步：跳转
        //根据不同的用户角色，跳转到不同的主菜单界面
        //无限循环，直到用户选择退出
        while (true) {
            switch (currentUser.getRole()) {
                case "admin":
                    showAdminMenu(currentUser);
                    break;
                case "teacher":
                    showTeacherMenu(currentUser);
                    break;
                case "monitor":
                    showMonitorMenu(currentUser);
                    break;
                case "student":
                    showStudentMenu(currentUser);
                    break;
                default:
                    System.out.println("未知角色，系统退出");
                    return;

            }
        }
    }
        //=====================登录逻辑=========================
        private User login(){
            System.out.println("\n ====登录界面====");
            System.out.println("请输入用户名：");
            String username = sc.nextLine();
            System.out.println("请输入密码：");
            String password = sc.nextLine();

            AuthServiceImpl authService = new AuthServiceImpl();
        try{
            User user = authService.login(username,password);
            System.out.println("登录成功！欢迎" + user.getUsername()+"(" +
                    user.getRole() + ")");
            return user;
        }catch(Exception e){
            System.out.println("登录失败：" + e.getMessage());
            return null;
        }
    }


    //=========管理员菜单（行政人员）===========
    private void showAdminMenu(User user) {
        while (true) {
            System.out.println("\n ===行政人员管理菜单===");
            System.out.println("1.添加学生");
            System.out.println("2.删除学生");
            System.out.println("3.修改学生信息");
            System.out.println("4.查询学生信息");
            System.out.println("5.退出登录");
            System.out.println("请选择您的操作：");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("---添加学生---");
                    System.out.println("请输入学号：");
                    Long id = sc.nextLong();
                    sc.nextLine();
                    System.out.println("请输入姓名：");
                    String name = sc.nextLine();
                    System.out.println("请输入班级ID:");
                    Long classId = sc.nextLong();
                    sc.nextLine();

                    try{
                        Student student = new Student();
                        student.setId(Math.toIntExact(id));
                        student.setName(name);
                        student.setClassId(Math.toIntExact(classId));
                        studentService.addStudent(student);
                        System.out.println("添加学生成功！");
                    }catch(Exception e){
                        System.out.println("操作失败" + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("请输入要删除的学号：");
                    Long delId = sc.nextLong();
                    sc.nextLine();

                    try{
                        studentService.deleteStudent(delId);
                        System.out.println("删除学生成功！");
                    }catch (Exception e){
                        System.out.println("操作失败" + e.getMessage());
                    }
                    break;
                case 3:
                    //功能略复杂，先占位
                    System.out.println("修改学生信息");
                    break;
                case 4:
                    System.out.println("请输入要查询的学号：");
                    Long findId = sc.nextLong();
                    sc.nextLine();

                    try{
                        Student s = studentService.getStudentById(findId);
                        System.out.println("查询结果：学号=" + s.getId() + ",姓名=" +s.getName()
                        + ",班级ID= " + s.getClassId());
                    }catch (Exception e){
                        System.out.println("操作失败：" + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("正在退出登录……");
                    return;
                default:
                    System.out.println("无效输入，请重新输入。");
            }
        }
    }
    //===============主课老师菜单============
    private void showTeacherMenu(User user){
        while(true){
            System.out.println("\n---主课老师菜单---");
            System.out.println("1.添加/修改成绩");
            System.out.println("2.删除成绩");
            System.out.println("3.查询班级成绩");
            System.out.println("4.退出登录");
            System.out.println("请选择操作：");
            int choice = sc.nextInt();sc.nextLine();

            switch(choice){
                case 1:
                    //这里要先进入课程（调用ITeacherCourseDao查询老师的任课）
                    System.out.println("请选择课程：");
                    //TODO : 调用scoreService.addOrUpdateScore()
                    System.out.println("成绩录入/修改成功！");
                    break;
                case 2:
                    System.out.println("删除成功！");
                    break;
                case 3:
                    System.out.println("查询班级成绩");
                    break;
                case 4:
                    System.out.println("退出登录……");
                default:
                    System.out.println("无效输入");
            }
        }
    }
    //============班长菜单===========
    private void showMonitorMenu(User user){
        while(true){
            System.out.println("\n---班长菜单---");
            System.out.println("1.查看全班成绩（含不及格名单）");
            System.out.println("2.退出登录");
            System.out.println("请选择操作：");
            int choice = sc.nextInt();sc.nextLine();

            switch(choice) {
                case 1:
                    //TODO : 调用scoreService.getClassScore()
                    System.out.println("全班成绩：…… 不及格：……");
                    break;
                case 2:
                    System.out.println("退出登录中……");
                    return;
                default:
                    System.out.println("无效输入");
            }
        }
    }
    //=============普通学生菜单============
    private void showStudentMenu(User user){
        while(true){
            System.out.println("\n---学生菜单---");
            System.out.println("1.查询我的成绩");
            System.out.println("2.退出登录");
            System.out.println("请选择操作：");
            int choice = sc.nextInt();sc.nextLine();

            switch(choice){
                case 1:
                    //TODO : 调用scoreService.getScoreByStudent()
                    System.out.println("你的成绩：");
                    break;
                case 2:
                    System.out.println("退出登录中……");
                    return;
                default:
                    System.out.println("无效输入");

            }
        }
    }

}
