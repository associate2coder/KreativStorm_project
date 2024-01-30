package com.springbootproject.object;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank(message = "name must have a value")
    private String name;

    @Column
    @Min(value = 1, message = "must have a value and be above 1")
    private int age;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "studentList")
//    private Course course = getDefaultCourse();
    private Course course;

    @Column
    @NotBlank(message = "email must have a value")
    @Email
    private String email;

    public Student(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

//    private Course getDefaultCourse() {
//        Course defaultCourse = new Course();
//        defaultCourse.setId(1);
//        defaultCourse.setName("Unassigned");
//        defaultCourse.setTeacher("No Teacher");
//        return defaultCourse;
//    }
}
