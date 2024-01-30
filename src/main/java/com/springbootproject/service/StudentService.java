package com.springbootproject.service;

import com.springbootproject.dto.StudentDto;
import com.springbootproject.object.Student;
import com.springbootproject.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentService implements StudentServiceInterface {

    @Autowired
    StudentRepository studentRepository;

    public long countAllTheStudentTableRows() {
        log.info("countAllTheRowsInTheStudentTable() was called");
        return studentRepository.count();
    }

    public StudentDto saveStudent(StudentDto studentdto) {
        log.info("save() was called");
        return studentRepository.save(studentdto);
    }

    public Student updateStudent(Student student) throws Exception {
        log.info("updateStudent() was called");
        Optional<Student> existingStudent = studentRepository.findById(student.getId());
        if (existingStudent.isEmpty()) {
            throw new Exception("Student with such id does not exist.");
        } else {
            existingStudent.get().setName(student.getName());
            existingStudent.get().setAge(student.getAge());
            existingStudent.get().setCourse(student.getCourse());
            existingStudent.get().setEmail(student.getEmail());
            return studentRepository.save(existingStudent.get());
        }
    }

    public List saveMultipleAtOnce(List<Student> studentList) {
        log.info("saveMultipleAtOnce() was called");
        return studentRepository.saveAll(studentList);
    }

    public boolean checkIfStudentExistsById(int id) {
        log.info("checkIfStudentExistsById() was called");
        return studentRepository.existsById(id);
    }

    public Optional<Student> findStudentById(int id) {
        log.info("findStudentById() was called");
        return studentRepository.findById(id);
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
