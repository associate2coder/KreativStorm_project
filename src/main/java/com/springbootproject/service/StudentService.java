package com.springbootproject.service;

import com.springbootproject.dto.StudentDto;
import com.springbootproject.exception.StudentDtoNullException;
import com.springbootproject.exception.StudentNullException;
import com.springbootproject.exception.StudentWithSuchAnIdDoesNotExistException;
import com.springbootproject.object.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    long countAllTheStudentTableRows();

    Student saveStudent(StudentDto studentDto) throws StudentDtoNullException;

    List<Student> saveMultipleStudentsAtOnce(List<StudentDto> studentDtoList) throws StudentDtoNullException;

    Student updateStudent(StudentDto studentdto) throws StudentWithSuchAnIdDoesNotExistException, StudentDtoNullException;

    boolean checkIfStudentExistsById(int id) throws StudentWithSuchAnIdDoesNotExistException;

    Optional<Student> findStudentById(int id) throws StudentWithSuchAnIdDoesNotExistException;

    List<Student> findAllStudents();

    List<StudentDto> findAllStudentsButReturnAsStudentDto();

    void deleteStudentById(int id);

    Class checkClassOfStudentTable();
}
