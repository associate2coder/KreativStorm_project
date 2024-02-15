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
    boolean checkIfStudentDtoIsValid(StudentDto studentDto) throws StudentDtoNullException; // TODO @LeksUkr: we can avoid this method just by adding @Validated annotation to StudentDto argument. No need for exception as @Validated already throws appropriate exception

    boolean checkIfStudentIsValid(Student student) throws StudentNullException; // TODO @LeksUkr: if we write methods saveStudent and  updateStudent correctly and do not save bad quality data to database, there is no need for this check (at least at the interface level)

    long countAllTheStudentTableRows() throws StudentTableIsEmptyException; // TODO @LeksUkr: why do we need exception here. If table is empty, it is reasonable to return 0

    Student saveStudent(StudentDto studentDto) throws StudentDtoNullException; // TODO @LeksUkr: as above, validation should be at Dto level with @Validated annotation. No need for custom exception

    List<Student> saveMultipleStudentsAtOnce(List<StudentDto> studentDtoList) throws StudentDtoNullException; // TODO @LeksUkr validation to be moved to Dto level. No need for custom exception

    Student updateStudent(StudentDto studentdto) throws StudentWithSuchAnIdDoesNotExistException, StudentDtoNullException; // TODO @LeksUkr: StudentWithSuchAnIdDoesNotExistException can be abstracted to ElementNotFoundException (and can be reused for similar purposes with Courses, Teachers, etc.). Validation to be moved to Dto Level with no need for StudentDtoNullException

    boolean checkIfStudentExistsById(Optional<Student> student) throws StudentWithSuchAnIdDoesNotExistException; //TODO @LeksUkr: method name suggests that you search by id and not by Optional. Moreover, Optional has its own methods to check whether it has value inside.

   Student findStudentById(int id) throws StudentWithSuchAnIdDoesNotExistException; // TODO @LeksUkr: generalize the exception

    List<Student> findAllStudents() throws StudentTableIsEmptyException; // TODO @LeksUkr: do not need an exception. If there are no students, just return an empty list

    List<StudentDto> findAllStudentsButReturnAsStudentDtoList(); // TODO @LeksUkr I would re-write method as "mapStudentToStudentDto" where you accept Student and return Dto object. Do not need to repeat findAll logic which you have already implemented.

    void deleteStudentById(int id) throws StudentWithSuchAnIdDoesNotExistException; // TODO @LeksUkr use your findStudentById or checkIfStudentExistsById to check if Student exists. If you do, you won't need exception here, it will be thrown at the level of those methods.

    Class checkClassOfStudentTable(); // TODO @LeksUkr: this is unnecesary
}
