package com.springbootproject.service;

import com.springbootproject.dto.StudentDto;
import com.springbootproject.exception.StudentWithSuchAnIdDoesNotExistException;
import com.springbootproject.object.Student;
import com.springbootproject.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    public long countAllTheStudentTableRows() {
        log.info("countAllTheRowsInTheStudentTable() was called");
        return studentRepository.count();
    }

    public Student saveStudent(StudentDto studentDto) {
        log.info("save() was called");
        Student student = new Student(studentDto.getId(), studentDto.getName(),studentDto.getAge(),studentDto.getEmail());
        try {
            return studentRepository.save(student);
        } catch (DataIntegrityViolationException e) {
            log.error("Data integrity violation during student save():");
            throw new RuntimeException("Error saving student." + e.getMessage(), e);
        }
    }

    public List<StudentDto> saveMultipleStudentsAtOnce(List<StudentDto> studentList) {
//        log.info("saveMultipleStudentsAtOnce() was called");
//
//        for (int i = 0; i < studentList.size(); i++) {
//            StudentDto student = studentList.get(i);
//            System.out.println("Student ID: " + student.getId());
//            System.out.println("Student Name: " + student.getName());
//            System.out.println("Student Age: " + student.getAge());
//            System.out.println("Student Email: " + student.getEmail());
//            // Add any additional fields you want to display
//
//            System.out.println("-----------------------");
//        }
//        Student student = new Student(studentDto.getName(),studentDto.getAge(),studentDto.getEmail());
//        return studentRepository.saveAll(studentList);
        return null;
    }

    public Student updateStudent(StudentDto studentdto) throws StudentWithSuchAnIdDoesNotExistException {
        log.info("updateStudent() was called");
        Optional<Student> existingStudent = studentRepository.findById(studentdto.getId());
        if (existingStudent.isEmpty()) {
            throw new StudentWithSuchAnIdDoesNotExistException("Student with such an id does not exist.");
        } else {
            existingStudent.get().setName(studentdto.getName());
            existingStudent.get().setAge(studentdto.getAge());
            existingStudent.get().setEmail(studentdto.getEmail());
            return studentRepository.save(existingStudent.get());
        }
    }

    public boolean checkIfStudentExistsById(int id) throws StudentWithSuchAnIdDoesNotExistException {
        log.info("checkIfStudentExistsById() was called");
        if (studentRepository.existsById(id)) {
            return studentRepository.existsById(id);
        } else {
            throw new StudentWithSuchAnIdDoesNotExistException("Student with such an id does not exist.");
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

    public List<Student> findAllStudents() {
        log.info("finAll() was called");
        return studentRepository.findAll();
    }

    public void deleteStudentById(int id) {
        log.info("deleteStudentById() was called");
        studentRepository.deleteById(id);
    }

    public Class checkClassOfStudentTable() {
        log.info("checkClass() was called");
        return studentRepository.getClass();
    }
}
