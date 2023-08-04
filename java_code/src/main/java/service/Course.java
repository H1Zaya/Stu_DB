package service;

import entity.CourseDO;
import entity.StuDO;
import req.StudentRequest;
import res.TableDTO;

public interface Course {
    TableDTO retrieveCourse(StudentRequest request);

    boolean add(CourseDO courseDO);
    boolean update(CourseDO courseDO);
    boolean delete(CourseDO courseDO);
}
