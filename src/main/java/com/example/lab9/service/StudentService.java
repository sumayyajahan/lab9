package com.example.lab9.service;

import com.example.lab9.entity.Student;

import java.util.Optional;

public interface StudentService {

    Student addStudent(Student newStudent);

    Iterable<Student> getAllStudent();

    Optional<Student> getStudentById(Integer studentId);



    Student updateStudentById(Integer studentId, Student editedStudent);

    String deleteStudentById(Integer studentId);

}
