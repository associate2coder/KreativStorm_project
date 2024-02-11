package com.springbootproject.service;

import com.springbootproject.dto.student.StudentDto;
import com.springbootproject.exception.student.StudentDtoNullException;
import com.springbootproject.exception.student.StudentNullException;
import com.springbootproject.exception.student.StudentTableIsEmptyException;
import com.springbootproject.exception.student.StudentWithSuchAnIdDoesNotExistException;
import com.springbootproject.object.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    boolean checkIfStudentDtoIsValid(StudentDto studentDto) throws StudentDtoNullException;

    boolean checkIfStudentIsValid(Student student) throws StudentNullException;

    long countAllTheStudentTableRows() throws StudentTableIsEmptyException;

    Student saveStudent(StudentDto studentDto) throws StudentDtoNullException;

    List<Student> saveMultipleStudentsAtOnce(List<StudentDto> studentDtoList) throws StudentDtoNullException;

    Student updateStudent(StudentDto studentdto) throws StudentWithSuchAnIdDoesNotExistException, StudentDtoNullException;

    boolean checkIfStudentExistsById(Optional<Student> student) throws StudentWithSuchAnIdDoesNotExistException;

   Student findStudentById(int id) throws StudentWithSuchAnIdDoesNotExistException;

    List<Student> findAllStudents() throws StudentTableIsEmptyException;

    List<StudentDto> findAllStudentsButReturnAsStudentDtoList();

    void deleteStudentById(int id) throws StudentWithSuchAnIdDoesNotExistException;

    Class checkClassOfStudentTable();
}
