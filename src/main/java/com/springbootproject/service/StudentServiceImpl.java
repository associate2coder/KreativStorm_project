package com.springbootproject.service;

import com.springbootproject.dto.StudentDto;
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

    public boolean checkIfStudentDtoIsNotEmpty(StudentDto studentDto) throws StudentDtoNullException {
        log.info("checkIfStudentDtoIsNotEmpty() was called");
        if (studentDto != null && !studentDto.getName().isBlank() && 1 <= studentDto.getAge()
                && !studentDto.getEmail().isBlank()) {
            return true;
        } else {
            throw new StudentDtoNullException("StudentDto is null or contains empty fields. Method: checkIfStudentDtoIsNotEmpty() in StudentServiceImpl");
        }
    }
    public boolean checkIfStudentIsNotEmpty(Student student) throws StudentNullException {
        log.info("checkIfStudentIsNotEmpty() was called");

        if (student != null && student.getId() != 0 && !student.getName().isBlank() && 1 <= student.getAge() && !student.getEmail().isBlank()) {
            return true;
        } else {
            throw new StudentNullException("Student is null or contains empty fields. Method: checkIfStudentIsNotEmpty() in StudentServiceImpl");
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
        if (checkIfStudentDtoIsNotEmpty(studentDto)) {
            Student student = new Student(studentDto.getId(), studentDto.getName(), studentDto.getAge(), studentDto.getEmail());
            return studentRepository.save(student);
        } else {
            throw new StudentDtoNullException("StudentDto is null in StudentServiceImpl saveStudent()");
        }
    }

    public List<Student> saveMultipleStudentsAtOnce(List<StudentDto> studentDtoList) throws StudentDtoNullException {
        log.info("saveMultipleStudentsAtOnce() was called");
        List<Student> studentList = new ArrayList<>();

        for (int i = 0; i < studentDtoList.size(); i++) {
            if (studentDtoList != null && studentDtoList.get(i) != null && checkIfStudentDtoIsNotEmpty(studentDtoList.get(i))) {
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
        if (checkIfStudentDtoIsNotEmpty(studentDto)) {
            Optional<Student> existingStudent = studentRepository.findById(studentDto.getId());
            if (checkIfStudentExistsById(existingStudent.get().getId())) {
                throw new StudentWithSuchAnIdDoesNotExistException("Student with such an id does not exist. Method: updateStudent() in StudentServiceImpl");
            } else {
                existingStudent.get().setName(studentDto.getName());
                existingStudent.get().setAge(studentDto.getAge());
                existingStudent.get().setEmail(studentDto.getEmail());
                return studentRepository.save(existingStudent.get());
            }
        } else {
            throw new StudentDtoNullException("StudentDto is null in StudentServiceImpl updateStudent()");
        }
    }

    public boolean checkIfStudentExistsById(int id) throws StudentWithSuchAnIdDoesNotExistException {
        log.info("checkIfStudentExistsById() was called");
        if (studentRepository.existsById(id)) {
            return studentRepository.existsById(id);
        } else {
            throw new StudentWithSuchAnIdDoesNotExistException("Student with such an id does not exist. Method: checkIfStudentExistsById() in StudentServiceImpl");
        }
    }

    public Optional<Student> findStudentById(int id) throws StudentWithSuchAnIdDoesNotExistException {
        log.info("findStudentById() was called");
        if (studentRepository.findById(id) != null) {
            return studentRepository.findById(id);
        } else {
            throw new StudentWithSuchAnIdDoesNotExistException("Student with such an id does not exist.");
        }
    }

    public List<StudentDto> findAllStudentsButReturnAsStudentDto() {
        List<StudentDto> listOfCurrentStudentsWithValuesButConvertedToStudentDTO = new ArrayList<>();
        log.info("findAllStudentsButReturnAsStudentDto() was called");
        List<Student> studentListWithActualValues = findAllStudents();
        for (int i = 1; i < studentListWithActualValues.size(); i++) {
            if (studentListWithActualValues.get(i) != null && checkIfStudentIsNotEmpty(studentListWithActualValues.get(i))) {
                StudentDto studentDto = new StudentDto();
                studentDto.setId(studentListWithActualValues.get(i).getId());
                studentDto.setName(studentListWithActualValues.get(i).getName());
                studentDto.setAge(studentListWithActualValues.get(i).getAge());
                studentDto.setEmail(studentListWithActualValues.get(i).getEmail());
                listOfCurrentStudentsWithValuesButConvertedToStudentDTO.add(studentDto);
            } else {
                throw new StudentNullException("Student in studentTable is null and therefore invalid. Method: findAllStudentsButReturnAsStudentDto() in StudentServiceImpl");
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
