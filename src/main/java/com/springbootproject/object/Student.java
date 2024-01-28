package com.springbootproject.object;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    private int age;

    @NotBlank(message = "course must have a value")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "studentList")
    private Course course;

    @Column
    @NotBlank(message = "email must have a value")
    @Email
    private String email;
}
