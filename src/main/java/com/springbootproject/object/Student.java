package com.springbootproject.object;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank(message = "name must have a value") // TODO @LeksUkr: This validation should be at the Dto level
    private String name;

    @Column
    @Min(value = 1, message = "must have a value and be above 1") // TODO @LeksUkr: This validation should be at the Dto level
    private int age;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "studentList")
    private Course course;

    @Column
    @NotBlank(message = "email must have a value") // TODO @LeksUkr: This validation should be at the Dto level
    @Email // TODO @LeksUkr: This validation should be at the Dto level
    private String email;

    public Student(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
