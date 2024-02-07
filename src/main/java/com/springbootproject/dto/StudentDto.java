package com.springbootproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
