package com.example.lab9;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.lab9.entity.Classroom;
import com.example.lab9.entity.Student;
import com.example.lab9.entity.Transcript;
import com.example.lab9.repository.StudentRepository;
import com.example.lab9.repository.TranscriptRepository;
import com.example.lab9.service.ClassroomService;
import com.example.lab9.service.StudentService;
import com.example.lab9.service.TranscriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@SpringBootApplication
public class Lab9Application implements CommandLineRunner {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private TranscriptService transcriptService;

    public static void main(String[] args) {

        SpringApplication.run(Lab9Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Java Application run.....");

        String dateString = "01-01-2012";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date= new Date();
        try {
            date = formatter.parse(dateString);
             }
        catch (ParseException e) {
            e.printStackTrace();
        }


        var anna = new Student(null, 001,"Anna", "Lynn", "Smith", 3.45, date, null);
        var saveAnna = createNewStudent(anna);
        var bob = new Student(null, 002,"Bob","Lynn", "Galverston", 2.87, date,null);
        var saveBob = createNewStudent(bob);



        var transcriptData = new Transcript(null,"BSC-4", null);
        var saveTransForAnna = transcriptService.addTranscript(transcriptData);


        saveAnna.setTranscript(saveTransForAnna);
        var updateAnna = updateStudent(saveAnna);

        var classoomData = new Classroom(null,"Mahabir", 123, null);
        var savedClassroom = classroomService.addClassroom(classoomData);

        savedClassroom.setStudents(Arrays.asList(anna,bob));
        classroomService.updateClassroom(savedClassroom.getClassroomId(), savedClassroom);

        System.out.println(savedClassroom);
        for(Student student: savedClassroom.getStudents()){
            System.out.println(student);
            System.out.println(student.getTranscript());

        }


        //read all students
        var students = getAllStudents();

        for(Student student : students){
            System.out.println(student);
        }

        //read student by id
        var studentId = 2;
        var student = getStudentById(studentId);
        if(student != null){
            System.out.printf("Student Id %d is: %s\n ",studentId, student);
        }else{
            System.out.printf("Student with Id %d doesn't exist: ", studentId);
        }
        //update student
        var updatedStudent = updateStudent(studentId);
        System.out.println(updatedStudent);
        System.out.printf("Updated Student Id %d is: %s\n ",studentId, student);

       // delete by id
        var deleteStatus = studentService.deleteStudentById(studentId);
        System.out.println(deleteStatus);


    }

    //insert student object
    private Student createNewStudent(Student student){

        return studentService.addStudent(student);
    }

    //read all students
    private Iterable<Student> getAllStudents(){
        //return studentRepository.findAll();
        return studentService.getAllStudent();
    }
    //get student by id
    private Student getStudentById(Integer studentId){
        return studentService.getStudentById(studentId).orElse(null);
    }
    //update student information

    private Student updateStudent(Student student){
        return studentService.updateStudentById(student.getStudentId(), student);
    }
    private Student updateStudent(Integer studentId){
     var oneStudentId =  studentService.getStudentById(studentId);

     if(oneStudentId.isPresent()){

         var getStudentInfo = oneStudentId.get();
         getStudentInfo.setFirstName("Sumayya");
         getStudentInfo.setLastName("Jahan");

         return studentService.updateStudentById(studentId, getStudentInfo);
     }else {
         return null;
     }
    }
}
