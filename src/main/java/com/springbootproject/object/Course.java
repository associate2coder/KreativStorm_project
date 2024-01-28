package com.springbootproject.object;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "teacher", nullable = false)
    private String teacher;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Student> studentList;
}
