package com.springbootproject.service;

import com.springbootproject.dto.StudentDto;
import com.springbootproject.object.Student;

import java.util.List;
import java.util.Optional;

public interface StudentServiceInterface {

    long countAllTheStudentTableRows();

    StudentDto saveStudent(StudentDto studentdto);

    Student updateStudent(Student student) throws Exception;

    boolean checkIfStudentExistsById(int id);

    Optional<Student> findStudentById(int id);

    List<Student> findAllStudents();

    void deleteStudentById(int id);

    Class checkClassOfStudentTable();
}
