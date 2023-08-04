package service;

import entity.TakesDO;
import req.StudentRequest;
import res.TableDTO;

public interface Takes {
    TableDTO retrieveTakes(StudentRequest request);

    boolean add(TakesDO takesDO);
    boolean update(TakesDO takesDO);
    boolean delete(TakesDO takesDO);
}
