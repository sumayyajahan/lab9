package com.example.lab9.service.impl;

import com.example.lab9.entity.Student;
import com.example.lab9.repository.StudentRepository;
import com.example.lab9.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student addStudent(Student newStudent) {
        return studentRepository.save(newStudent);
    }

    @Override
    public Iterable<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Integer studentId) {

        return studentRepository.findById(studentId);
    }

    @Override
    public Student updateStudentById(Integer studentId, Student editedStudent) {
        var theStudent = studentRepository.findById(studentId).orElse(null);
         Student updatedStudent = null;
        if(theStudent != null){

            theStudent.setFirstName(editedStudent.getFirstName());
            theStudent.setLastName(editedStudent.getLastName());

            updatedStudent = studentRepository.save(theStudent);
        }
        return updatedStudent;
    }

    @Override
    public String deleteStudentById(Integer studentId) {
        var theStudent = studentRepository.findById(studentId).orElse(null);
        if(theStudent != null){
             studentRepository.deleteById(studentId);
            return String.format("Sudent is deleted", studentId);
        }else {
            return String.format("Student id is not found", studentId);
        }



    }
}
