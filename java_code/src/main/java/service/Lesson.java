package service;

import entity.CourseDO;
import entity.LessonDO;
import req.StudentRequest;
import res.TableDTO;

public interface Lesson {
    TableDTO retrieveLesson(StudentRequest request);

    boolean add(LessonDO lessonDO);
    boolean update(LessonDO lessonDO);
    boolean delete(LessonDO lessonDO);
}
