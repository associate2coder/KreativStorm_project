package com.springbootproject.service;

import com.springbootproject.dto.StudentDto;
import com.springbootproject.exception.StudentWithSuchAnIdDoesNotExistException;
import com.springbootproject.object.Student;

import java.util.List;
import java.util.Optional;

public interface StudentServiceInterface {

    long countAllTheStudentTableRows();

    StudentDto saveStudent(StudentDto studentdto);

    List<StudentDto> saveMultipleStudentsAtOnce(List<StudentDto> studentList);

    Student updateStudent(StudentDto studentdto) throws StudentWithSuchAnIdDoesNotExistException;

    boolean checkIfStudentExistsById(int id) throws StudentWithSuchAnIdDoesNotExistException ;

    Optional<Student> findStudentById(int id) throws StudentWithSuchAnIdDoesNotExistException ;

    List<Student> findAllStudents();

    void deleteStudentById(int id);

    Class checkClassOfStudentTable();
}
