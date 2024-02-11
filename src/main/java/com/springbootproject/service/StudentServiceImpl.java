package com.springbootproject.service;

import com.springbootproject.dto.student.StudentDto;
import com.springbootproject.exception.student.*;
import com.springbootproject.object.Student;
import com.springbootproject.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    public boolean checkIfStudentDtoIsValid(StudentDto studentDto) throws StudentDtoNullException, StudentDtoWrongIdException,
            StudentDtoWrongNameException, StudentDtoWrongAgeException, StudentDtoWrongEmailException {
        log.info("checkIfStudentDtoIsValid() was called");
        if (studentDto == null) {
            throw new StudentDtoNullException("StudentDto is null or contains empty fields. Method: checkIfStudentDtoIsValid() in StudentServiceImpl");
        }
        if (studentDto.getId() <= 0) {
            throw new StudentDtoWrongIdException("StudentDto.getId() is 0 or below 0. Method: checkIfStudentDtoIsValid() in StudentServiceImpl");
        }
        if (studentDto.getName().isBlank()) {
            throw new StudentDtoWrongNameException("StudentDto.getName() is blank. Method: checkIfStudentDtoIsValid() in StudentServiceImpl");
        }
        if (studentDto.getAge() <= 0) {
            throw new StudentDtoWrongAgeException("StudentDto.getAge() is 0 or below 0. Method: checkIfStudentDtoIsValid() in StudentServiceImpl");
        }
        if (studentDto.getEmail().isBlank() || !isValidEmail(studentDto.getEmail())) {
            throw new StudentDtoWrongEmailException("StudentDto.getEmail() is blank. Method: checkIfStudentDtoIsValid() in StudentServiceImpl");
        }


        if (studentDto != null && 0 < studentDto.getId() && !studentDto.getName().isBlank() && 0 < studentDto.getAge()
                && !studentDto.getEmail().isBlank() && isValidEmail(studentDto.getEmail())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidEmail(String email) {
        String regex = "^[^@.]+@[^@.]+\\.[^@.]+$";
        return email.matches(regex);
    }

    public boolean checkIfStudentIsValid(Student student) throws StudentNullException, StudentWrongIdException,
            StudentWrongNameException, StudentWrongAgeException, StudentWrongEmailException {
        log.info("checkIfStudentIsValid() was called");
        if (student == null) {
            throw new StudentNullException("Student is null or contains empty fields. Method: checkIfStudentIsValid() in StudentServiceImpl");
        }
        if (student.getId() <= 0) {
            throw new StudentWrongIdException("Student.getId() is 0 or below 0. Method: checkIfStudentIsValid() in StudentServiceImpl");
        }
        if (student.getName().isBlank()) {
            throw new StudentWrongNameException("Student.getName() is blank. Method: checkIfStudentIsValid() in StudentServiceImpl");
        }
        if (student.getAge() <= 0) {
            throw new StudentWrongAgeException("Student.getAge() is 0 or below 0. Method: checkIfStudentIsValid() in StudentServiceImpl");
        }
        if (student.getEmail().isBlank() || !isValidEmail(student.getEmail())) {
            throw new StudentWrongEmailException("Student.getEmail() is blank. Method: checkIfStudentIsValid() in StudentServiceImpl");
        }


        if (student != null && 0 < student.getId() && !student.getName().isBlank() && 0 < student.getAge()
                && !student.getEmail().isBlank() && isValidEmail(student.getEmail())) {
            return true;
        } else {
            return false;
        }
    }

    public long countAllTheStudentTableRows() throws StudentTableIsEmptyException {
        log.info("countAllTheRowsInTheStudentTable() was called");
        long numberOfRows = studentRepository.count();

        if (numberOfRows <= 0) {
            throw new StudentTableIsEmptyException("Student table is empty. Number of rows is 0. Method: countAllTheStudentTableRows() in StudentServiceImpl");
        } else {
            return numberOfRows;
        }
    }

    public Student saveStudent(StudentDto studentDto) throws StudentDtoNullException {
        log.info("saveStudent() was called");
        if (checkIfStudentDtoIsValid(studentDto)) {
            Student student = new Student(
                    studentDto.getId(), studentDto.getName(), studentDto.getAge(), studentDto.getEmail()
            );
            return studentRepository.save(student);
        } else {
            throw new StudentDtoException("StudentDto values are wrong. Method: saveStudent() in StudentServiceImpl");
        }
    }

    public List<Student> saveMultipleStudentsAtOnce(List<StudentDto> studentDtoList) throws StudentDtoNullException {
        log.info("saveMultipleStudentsAtOnce() was called");
        List<Student> studentList = new ArrayList<>();

        for (int i = 0; i < studentDtoList.size(); i++) {
            if (studentDtoList != null && studentDtoList.get(i) != null && checkIfStudentDtoIsValid(studentDtoList.get(i))) {
                Student student = new Student();
                student.setId(studentDtoList.get(i).getId());
                student.setName(studentDtoList.get(i).getName());
                student.setAge(studentDtoList.get(i).getAge());
                student.setEmail(studentDtoList.get(i).getEmail());
                studentList.add(student);
            } else {
                throw new StudentDtoNullException("StudentDto is null in StudentServiceImpl saveMultipleStudentsAtOnce()");
            }
        }
        return studentRepository.saveAll(studentList);
    }

    public Student updateStudent(StudentDto studentDto) throws StudentWithSuchAnIdDoesNotExistException, StudentDtoNullException {
        log.info("updateStudent() was called");
        if (checkIfStudentDtoIsValid(studentDto)) {
            Student existingStudent = findStudentById(studentDto.getId());
                existingStudent.setName(studentDto.getName());
                existingStudent.setAge(studentDto.getAge());
                existingStudent.setEmail(studentDto.getEmail());
                return studentRepository.save(existingStudent);
        } else {
            throw new StudentDtoException("StudentDto is invalid. Method: updateStudent() in StudentServiceImpl");
        }
    }

    public boolean checkIfStudentExistsById(Optional<Student> student) throws StudentWithSuchAnIdDoesNotExistException {
        log.info("checkIfStudentExistsById() was called");
        if (studentRepository.existsById(student.get().getId())) {
            return true;
        } else {
            throw new StudentWithSuchAnIdDoesNotExistException("Student with such an id does not exist. Method: checkIfStudentExistsById() in StudentServiceImpl");
        }
    }

    public Student findStudentById(int id) throws StudentWithSuchAnIdDoesNotExistException {
        log.info("findStudentById() was called");
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent != null && optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else {
            throw new StudentWithSuchAnIdDoesNotExistException("Student with such an id does not exist. Method: findStudentById() in StudentServiceImpl");
        }
    }

    public List<StudentDto> findAllStudentsButReturnAsStudentDtoList() {
        List<StudentDto> listOfCurrentStudentsWithValuesButConvertedToStudentDTO = new ArrayList<>();
        log.info("findAllStudentsButReturnAsStudentDtoList() was called");
        List<Student> studentListWithActualValues = findAllStudents();
        for (int i = 1; i < studentListWithActualValues.size(); i++) {
            if (studentListWithActualValues.get(i) != null && checkIfStudentIsValid(studentListWithActualValues.get(i))) {
                StudentDto studentDto = new StudentDto();
                studentDto.setId(studentListWithActualValues.get(i).getId());
                studentDto.setName(studentListWithActualValues.get(i).getName());
                studentDto.setAge(studentListWithActualValues.get(i).getAge());
                studentDto.setEmail(studentListWithActualValues.get(i).getEmail());
                listOfCurrentStudentsWithValuesButConvertedToStudentDTO.add(studentDto);
            } else {
                throw new StudentNullException("Student in studentTable is null and therefore invalid. Method: findAllStudentsButReturnAsStudentDtoList() in StudentServiceImpl");
            }
        }
        return listOfCurrentStudentsWithValuesButConvertedToStudentDTO;
    }

    public List<Student> findAllStudents() throws StudentTableIsEmptyException {
        log.info("finAll() was called");
        List<Student> allStudents = studentRepository.findAll();

        if (allStudents.isEmpty()) {
            throw new StudentTableIsEmptyException("Student table does not have any students in it. Method: findAllStudents() is StudentServiceImpl");
        } else {
            return allStudents;
        }
    }

    public void deleteStudentById(int id) throws StudentWithSuchAnIdDoesNotExistException {
        log.info("deleteStudentById() was called");
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new StudentWithSuchAnIdDoesNotExistException("Student with such an id does not exist and therefore can not be deleted. Method: deleteStudentById() in StudentServiceImpl");
        }
    }

    public Class checkClassOfStudentTable() {
        log.info("checkClassOfStudentTable() was called");
        if (studentRepository.getClass() != null ) {
            return studentRepository.getClass();
        } else {
            throw new StudentTableDoesNotExistException("Student table does not exist. Method: checkClassOfStudentTable() in StudentServiceImpl");
        }
    }
}
