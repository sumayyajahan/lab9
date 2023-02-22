package com.example.lab9.service.impl;

import com.example.lab9.entity.Classroom;
import com.example.lab9.repository.ClassroomRepository;
import com.example.lab9.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Override
    public Classroom addClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    @Override
    public Iterable<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    @Override
    public Optional<Classroom> getClassroomById(Integer classroomId) {
        return classroomRepository.findById(classroomId);
    }

    @Override
    public Classroom updateClassroom(Integer classroomId, Classroom classroom) {

        var classroomDataById = classroomRepository.findById(classroomId).orElse(null);
        Classroom updateClassroom = null;
        if(classroomDataById != null){

            classroomDataById.setBuildingName(classroom.getBuildingName());
            classroomDataById.setRoomNumber(classroom.getRoomNumber());
            updateClassroom = classroomRepository.save(classroomDataById);

        }

        return updateClassroom;
    }

    @Override
    public String deleteClassroom(Integer classroomId) {
        var classroomDataById = classroomRepository.findById(classroomId).orElse(null);

        if(classroomDataById != null){
            classroomRepository.deleteById(classroomId);
            return String.format("Classroom is deleted", classroomId);
        }else {
            return String.format("Classroom id is not found", classroomId);
        }

    }
}
