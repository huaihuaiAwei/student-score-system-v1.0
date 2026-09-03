# 📚 学生成绩管理系统 v1.0

基于 JavaSE + MySQL 的控制台版学生成绩管理系统，采用分层架构与面向接口编程，实现多角色权限管理与成绩统计分析。

## ✨ 功能特性

- 🔐 **多角色登录**：行政人员 / 主课老师 / 班长 / 普通学生，不同角色展示不同菜单
- 📝 **学生档案管理**：增删改查学生信息
- 📊 **成绩管理**：录入、修改、删除、按课程排名、不及格自动标红
- 📈 **班级统计**：班长可查看全班各科平均分及不及格名单
- 🛠️ **工程规范**：分层架构（UI/Service/DAO）、面向接口编程、Git 规范化提交

## 🧰 技术栈

- **语言**：JavaSE 17
- **数据库**：MySQL 8.0
- **连接池**：JDBC (DriverManager)
- **日志**：SLF4J + Logback
- **构建工具**：Maven
- **版本控制**：Git

## 🚀 快速开始

### 环境准备
- JDK 17+
- MySQL 8.0+
- Maven 3.8+

### 运行步骤
1. 克隆项目：`git clone https://github.com/你的用户名/仓库名.git`
2. 导入 IDE，刷新 Maven 依赖
3. 执行 `/sql/score_system_v1.sql` 初始化数据库
4. 修改 `JDBCUtil` 中的数据库用户名/密码
5. 运行 `ConsoleUI.main()` 启动系统

### 测试账号
| 角色 | 用户名 | 密码 |
| :--- | :--- | :--- |
| 行政人员 | admin | 123456 |
| 主课老师 | zhanglao | 123456 |
| 班长 | xiaohong | 123456 |
| 普通学生 | xiaoming | 123456 |

## 📁 项目结构
com.score
├── pojo # 实体类 (User/Student/Score/Course...)
├── dao # 数据访问层接口及实现
├── service # 业务逻辑层接口及实现
├── ui # 控制台界面 (ConsoleUI)
└── util # 工具类 (JDBCUtil, BusinessException)
