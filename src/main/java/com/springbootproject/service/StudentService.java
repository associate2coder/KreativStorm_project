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
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public long countAllTheRows() {
        log.info("countAllTheRows() was called");
        return studentRepository.count();
    }

    public StudentDto save(StudentDto studentdto) {
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

    public boolean checkIfItExistsById(int id) {
        log.info("checkIfItExistsById() was called");
        return studentRepository.existsById(id);
    }

    public Optional<Student> findById(int id) {
        log.info("findById() was called");
        return studentRepository.findById(id);
    }

    public List<Student> findAll() {
        log.info("finAll() was called");
        return studentRepository.findAll();
    }

    public void deleteById(int id) {
        log.info("deleteById() was called");
        studentRepository.deleteById(id);
    }

    public Class checkClass() {
        log.info("checkClass() was called");
        return studentRepository.getClass();
    }
}
