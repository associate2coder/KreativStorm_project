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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "courseList")
    private Teacher teacher;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Student> studentList;

    // TODO @LeksUkr: capacity (int) field is needed to implement "Course is full" logic.

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                '}';
    }
}
