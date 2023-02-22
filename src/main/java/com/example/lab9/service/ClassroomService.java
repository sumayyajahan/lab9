package com.example.lab9.service;

import com.example.lab9.entity.Classroom;
import com.example.lab9.entity.Student;

import java.util.Optional;

public interface ClassroomService {

    Classroom addClassroom(Classroom classroom);

    Iterable<Classroom> getAllClassrooms();

    Optional<Classroom> getClassroomById(Integer classroomId);

    Classroom updateClassroom(Integer classroomId, Classroom classroom);

    String deleteClassroom(Integer classroomId);

}
