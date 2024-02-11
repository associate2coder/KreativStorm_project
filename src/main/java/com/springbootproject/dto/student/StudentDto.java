package com.springbootproject.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private int id;
    private String name;
    private int age;
    private String email;


//    public StudentDto(int id, String name, int age, String email) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.email = email;
//    }
//
//    public StudentDto(String name, int age, String email) {
//        this.name = name;
//        this.age = age;
//        this.email = email;
//    }
}
