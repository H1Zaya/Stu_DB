"# Stu_DB" 
# 数据库

### 赵峙尧 





md文档使用相关软件查看效果更佳

具体讲解在zayaDB.MP4

java代码在java_code

sql在mydb.sql

--------------------------------------------------



## 总体说明

实现了一个简单的教务处系统

包含

1. 学生的信息（学号、姓名、gpa、总学分、选课）
2. 课程信息（课程号、课序号、任课教师、学年、学期、对应学分）
3. 一个用于登录的管理员信息

另外数据库表的设计基于自己设计的**er图和模式图**，会在打包好的文件夹中出现

#### 功能说明

具体是由教务老师进行的操作（即管理员）

1. 教务老师可以增删改课程course

2. 教务老师可以在course的基础上增删改lesson并分配老师

   即一门课程不同班级

3. 教务老师可以增删改学生

4. 教务老师可以为指定学生增删改已有课程



### 具体实现

#### 后端

​	使用mysql的服务端，对数据库进行了基本的设计以便能达到预期的要求。最初设计时使用控制台命令进行建表等操作，但实在是难以管理并即时响应更改，后使用navicat进行调试和修改。得到最终的数据库。貌似navicat对本人的sql源码进行了更改，在导出时加入了大量设置信息，简单删减后应可以清晰体现所学知识。

1. 共建立了五个表，分别是 admin course student lesson 和 takes

   其中 admin 仅仅用于登录，与其他表无关联

2. 各表信息（**主码加粗、外码下划线**）**数据库中是英文标识**

   1. student表包含学生相关信息

      （**编号**、姓名、gpa、总学分、密码）

      1. 编号就是学号
      2. 姓名是学生姓名
      3. gpa是当前学生的平均绩点
      4. 总学分是当前学生已经拿到的总学分
      5. 密码是当前学生的教务处密码

   2. admin表包含管理员信息

      （用户名、**编号**、密码）用于登录

      1. 用户名是管理员名称
      2. 编号是管理员号码
      3. 密码是登录系统对应编号的密码

   3. course表包含课程信息

      （**课程号**、**学年**、**学期**、课程名、学分、考核方式、院系）

      1. 课程号是某课程的标识号，与学年和学期共同构成主码
      2. 学年是课程开设学年
      3. 学期是课程开设学期，限制在spring和fall之内
      4. 课程名是对应课程号的课程名称
      5. 学分是该课程的学分数
      6. 考核方式是该课程最后考核的方式、分为考试和考察
      7. 院系是开设该课程的院系

   4. lesson表包含每个课程对应的具体课的信息

      （**<u>课程号</u>**、**<u>学年</u>**、**<u>学期</u>**、**课序号**、教师、时间、教学楼、房间号）

      1. 课程号、学年、学期上面讲过、这里做外码和主码的一部分
      2. 课序号是对应某个课程的第几个课、一节课不够学生上
      3. 教师是某具体课程的任课教师
      4. 时间是上课的周数
      5. 教学楼略
      6. 房间号略

   5. takes表包含学生的选课信息

      （**<u>编号</u>**、**<u>课程号</u>**、**<u>学年</u>**、**<u>学期</u>**、**<u>课序号</u>**、gpa）

      1. 编号是学生表的编号、做外码和主码的子集
      2. 课程号和学年和学期和课序号来自lesson表、作外码并与编号构成主码
      3. gpa是由主码决定的该课程的成绩

3. 表之间由外码联系

4. 表内数据有完整性约束、一致性约束

   包括外码约束、非空约束、具体值具体范围约束  （具体见sql文件）

   **例如：**

   CONSTRAINT `学期是否异常` CHECK (`semester` in ('spring',_'fall'))

   CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT

5. 有关于触发器的设定，在对takes元组插入、删除、修改时会对对应的student表中的gpa和tot_credits进行相应的修改（具体见sql文件）

   **例如：**

   delimiter ;;
   CREATE TRIGGER `creditplus` AFTER INSERT ON `takes` FOR EACH ROW begin
   	DECLARE a float;
   	DECLARE tempcre int;
   	DECLARE tot int;
   	set tot = (SELECT tot_credits from student where student.ID = new.ID);
   	SET tempcre = (SELECT credits FROM course where course.course_id = new.course_id and course.course_year = new.course_year and course.semester = new.semester);
   	SET a = ((SELECT tot_GPA FROM student where student.ID = new.ID)*tot+tempcre*new.GPA)/(tot+tempcre);
   	UPDATE student set tot_credits = tot+tempcre WHERE student.ID = new.ID;
   	UPDATE student set tot_GPA = a WHERE student.ID = new.ID;
   	end
   ;;
   delimiter ;

6. 生成了一个用于查询某学生所有选课信息的**视图（view）**

   

#### 前端

前端基于java的JDBC对数据库进行连接，利用java代码实现具体的sql语句来对数据库进行增删查改的操作。并使用java的相关GUI包来进行简单的界面设计。

具体功能

1. 可以查看除管理员信息外的其他表的绝大部分信息
2. 支持对表项的增删查改
3. 表项在增删查改后实时刷新
4. 使用管理员信息进行系统的登录
5. 将更改过后的信息实时存储到数据库




