package service;

import entity.StuDO;
import req.StudentRequest;
import res.TableDTO;

public interface Student {
    public boolean validStu(StuDO stuDO);
    TableDTO retrieveStudents(StudentRequest request);

    boolean add(StuDO stuDO);
    boolean update(StuDO stuDO);
    boolean delete(StuDO stuDO);
}
