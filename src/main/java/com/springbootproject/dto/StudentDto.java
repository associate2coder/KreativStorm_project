package com.springbootproject.dto;

import com.springbootproject.object.Course;
import com.springbootproject.object.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto extends Student {
    private int id;
    private String name;
    private int age;
    private Course course;
    private String email;
}
