/*
 Navicat MySQL Data Transfer

 Source Server         : no
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : mydb

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 25/06/2022 20:11:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `ID` int NOT NULL,
  `userName` varchar(45) NOT NULL,
  `pwd` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE`(`ID` ASC)
)

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'zaya', '123456');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_id` varchar(45) NOT NULL,
  `course_name` varchar(45) NOT NULL,
  `course_year` varchar(45) NOT NULL,
  `semester` varchar(45) NOT NULL,
  `credits` smallint NOT NULL,
  `exam` varchar(45) NOT NULL,
  `department` varchar(45) NOT NULL,
  PRIMARY KEY (`course_id`, `course_year`, `semester`),
  INDEX `course_id`(`course_id` ASC),
  INDEX `course_year`(`course_year` ASC),
  INDEX `semester`(`semester` ASC),
  CONSTRAINT `学分异常` CHECK (`credits` between 1 and 6),
  CONSTRAINT `学期是否异常` CHECK (`semester` in (_utf8mb4'spring',_utf8mb4'fall'))
)

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('20190001', 'java', '2019', 'spring', 3, 'test', 'computer');
INSERT INTO `course` VALUES ('20190002', '数据库', '2019', 'spring', 4, 'test', 'computer');
INSERT INTO `course` VALUES ('20190003', '离散数学', '2019', 'fall', 5, 'test', 'computer');
INSERT INTO `course` VALUES ('20190003', '高数', '2019', 'spring', 6, 'test', 'math');
INSERT INTO `course` VALUES ('20200001', '计算机网络', '2020', 'fall', 4, 'test', 'computer');

-- ----------------------------
-- Table structure for lesson
-- ----------------------------
DROP TABLE IF EXISTS `lesson`;
CREATE TABLE `lesson`  (
  `lesson_id` int NOT NULL,
  `course_id` varchar(45) NOT NULL,
  `course_year` varchar(45) NOT NULL,
  `semester` varchar(45) NOT NULL,
  `teacher` varchar(45) NOT NULL,
  `days` varchar(45) NOT NULL,
  `building` varchar(45) NOT NULL,
  `room_no` smallint NOT NULL,
  PRIMARY KEY (`lesson_id`, `course_id`, `course_year`, `semester`),
  INDEX `course_id`(`course_id` ASC),
  INDEX `course_year`(`course_year` ASC),
  INDEX `semester`(`semester` ASC),
  INDEX `lesson_id`(`lesson_id` ASC),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `course_year` FOREIGN KEY (`course_year`) REFERENCES `course` (`course_year`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `semester` FOREIGN KEY (`semester`) REFERENCES `course` (`semester`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `checklesson` CHECK (`lesson_id` between 1 and 10)
)

-- ----------------------------
-- Records of lesson
-- ----------------------------
INSERT INTO `lesson` VALUES (1, '20190001', '2019', 'spring', 'wangwu', '12weeks', 'no_2', 102);
INSERT INTO `lesson` VALUES (1, '20190002', '2019', 'spring', 'xing', '16weeks', 'no_1', 404);
INSERT INTO `lesson` VALUES (1, '20190003', '2019', 'fall', 'jack', '18weeks', 'no_3', 221);
INSERT INTO `lesson` VALUES (1, '20190003', '2019', 'spring', 'guanyu', '16weeks', 'no_1', 108);
INSERT INTO `lesson` VALUES (1, '20200001', '2020', 'fall', 'linfeng', '17weeks', 'no_4', 105);
INSERT INTO `lesson` VALUES (2, '20190001', '2019', 'spring', 'klieen', '12weeks', 'no_1', 201);
INSERT INTO `lesson` VALUES (2, '20190002', '2019', 'spring', 'yijing', '16weeks', 'no_2', 328);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `ID` int NOT NULL,
  `student_name` varchar(45) NOT NULL,
  `tot_GPA` float(3, 2) NOT NULL,
  `tot_credits` int NOT NULL DEFAULT 0,
  `pwd` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
)

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'zaya', 4.00, 7, '123456');
INSERT INTO `student` VALUES (2, 'nance', 3.14, 11, '123456');
INSERT INTO `student` VALUES (3, 'li', 0.00, 0, '123456');

-- ----------------------------
-- Table structure for takes
-- ----------------------------
DROP TABLE IF EXISTS `takes`;
CREATE TABLE `takes`  (
  `ID` int NOT NULL,
  `course_year` varchar(45) NOT NULL,
  `semester` varchar(45) NOT NULL,
  `course_id` varchar(45) NOT NULL,
  `lesson_id` int NOT NULL,
  `GPA` float NOT NULL DEFAULT 0,
  PRIMARY KEY (`ID`, `course_year`, `semester`, `course_id`, `lesson_id`),
  INDEX `c_year`(`course_year` ASC),
  INDEX `c_id`(`course_id` ASC),
  INDEX `lesson_id`(`lesson_id` ASC),
  INDEX `semse`(`semester` ASC),
  CONSTRAINT `c_id` FOREIGN KEY (`course_id`) REFERENCES `lesson` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `c_year` FOREIGN KEY (`course_year`) REFERENCES `lesson` (`course_year`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `lesson_id` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`lesson_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `semse` FOREIGN KEY (`semester`) REFERENCES `lesson` (`semester`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `stu_id` FOREIGN KEY (`ID`) REFERENCES `student` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `检查gpa` CHECK (`GPA` between 1 and 4)
)

-- ----------------------------
-- Records of takes
-- ----------------------------
INSERT INTO `takes` VALUES (1, '2019', 'spring', '20190001', 1, 4);
INSERT INTO `takes` VALUES (1, '2019', 'spring', '20190002', 1, 4);
INSERT INTO `takes` VALUES (2, '2019', 'fall', '20190003', 1, 3.3);
INSERT INTO `takes` VALUES (2, '2019', 'spring', '20190003', 1, 3);

-- ----------------------------
-- View structure for stu_course
-- ----------------------------
DROP VIEW IF EXISTS `stu_course`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `stu_course` AS select `takes`.`ID` AS `ID`,`takes`.`course_id` AS `course_id`,`takes`.`course_year` AS `course_year`,`takes`.`semester` AS `semester`,`takes`.`lesson_id` AS `lesson_id`,`course`.`credits` AS `credits` from (`takes` join `course` on(((`takes`.`course_year` = `course`.`course_year`) and (`takes`.`semester` = `course`.`semester`) and (`takes`.`course_id` = `course`.`course_id`))));

-- ----------------------------
-- Triggers structure for table takes
-- ----------------------------
DROP TRIGGER IF EXISTS `credit_delete`;
delimiter ;;
CREATE TRIGGER `credit_delete` AFTER DELETE ON `takes` FOR EACH ROW BEGIN
DECLARE N_GPA FLOAT;
DECLARE N_credit int;
DECLARE tempww int;
SET tempww = (SELECT credits FROM course where course.course_id = old.course_id and course.course_year = old.course_year and course.semester = old.semester);
SET N_credit = (SELECT tot_credits from student WHERE student.ID = OLD.ID);
SET N_GPA = ((SELECT tot_GPA from student WHERE student.ID = old.ID)*N_credit - tempww*old.GPA)/(N_credit-tempww);
UPDATE student set tot_credits = N_credit - tempww WHERE student.ID = old.ID;
UPDATE student set tot_GPA = N_GPA WHERE student.ID = old.ID;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table takes
-- ----------------------------
DROP TRIGGER IF EXISTS `credit_alter`;
delimiter ;;
CREATE TRIGGER `credit_alter` AFTER UPDATE ON `takes` FOR EACH ROW BEGIN
DECLARE N_GPA FLOAT;
DECLARE N_credit int;
DECLARE tempww int;
SET tempww = (SELECT credits FROM course where course.course_id = old.course_id and course.course_year = old.course_year and course.semester = old.semester);
SET N_credit = (SELECT tot_credits from student WHERE student.ID = OLD.ID);
SET N_GPA = (((SELECT tot_GPA from student WHERE student.ID = old.ID)*N_credit - tempww*old.GPA+tempww*new.GPA)/N_credit);
UPDATE student set tot_GPA = N_GPA WHERE student.ID = new.ID;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table takes
-- ----------------------------
DROP TRIGGER IF EXISTS `creditplus`;
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

SET FOREIGN_KEY_CHECKS = 1;
