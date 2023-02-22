package com.example.lab9.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Classroom {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer classroomId;
     private String buildingName;
     private Integer roomNumber;

     @ManyToMany(cascade = CascadeType.ALL)
     @JoinTable(name = "classrooms_students",
             joinColumns = {@JoinColumn(name = "student_id")},
             inverseJoinColumns = {@JoinColumn(name = "classroom_id")})
     private List<Student> students;

     @Override
     public String toString() {
          return "Classroom{" +
                  "classroomId=" + classroomId +
                  ", buildingName='" + buildingName + '\'' +
                  ", roomNumber=" + roomNumber +
                  '}';
     }
}
