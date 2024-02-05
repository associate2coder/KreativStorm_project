package com.springbootproject.service;

import com.springbootproject.dto.StudentDto;
import com.springbootproject.exception.StudentDtoNullException;
import com.springbootproject.exception.StudentNullException;
import com.springbootproject.exception.StudentWithSuchAnIdDoesNotExistException;
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
        if (studentDto != null && studentDto.getId() != 0 && !studentDto.getName().isBlank() && !studentDto.getName().isEmpty()
                && !studentDto.getEmail().isEmpty() && !studentDto.getEmail().isBlank()) {
            return false;
        } else {
            throw new StudentDtoNullException("StudentDto is null in StudentServiceImpl checkIfStudentDtoIsEmpty()");
        }
    }

    public boolean checkIfStudentIsNotEmpty(Student student) throws StudentNullException {
        if (student != null && student.getId() != 0 && !student.getName().isBlank() && !student.getName().isEmpty()
                && !student.getEmail().isEmpty() && !student.getEmail().isBlank()) {
            return false;
        } else {
            throw new StudentDtoNullException("Student is null in StudentServiceImpl checkIfStudentIsEmpty()");
        }
    }

    public long countAllTheStudentTableRows() {
        log.info("countAllTheRowsInTheStudentTable() was called");
        return studentRepository.count();
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

    public List<StudentDto> findAllStudentsButReturnAsStudentDto() throws StudentNullException {
        List<StudentDto> listOfCurrentStudentsWithValuesButConvertedToStudentDTO = new ArrayList<>();
        log.info("findAllStudentsButReturnAsStudentDto() was called");
        List<Student> studentListWithActualValues = studentRepository.findAll();
        for (int i = 0; i < studentListWithActualValues.size(); i++) {
            if (studentListWithActualValues.get(i) != null && checkIfStudentIsNotEmpty(studentListWithActualValues.get(i))) {
                StudentDto studentDto = new StudentDto();
                studentDto.setId(studentListWithActualValues.get(i).getId());
                studentDto.setName(studentListWithActualValues.get(i).getName());
                studentDto.setAge(studentListWithActualValues.get(i).getAge());
                studentDto.setEmail(studentListWithActualValues.get(i).getEmail());
                listOfCurrentStudentsWithValuesButConvertedToStudentDTO.add(studentDto);
            } else {
                throw new StudentNullException("StudentDto is null in StudentServiceImpl findAllStudentsButReturnAsStudentDto()");
            }
        }
        return listOfCurrentStudentsWithValuesButConvertedToStudentDTO;
    }

    public List<Student> findAllStudents() {
        log.info("finAll() was called");
        return studentRepository.findAll();
    }

    public void deleteStudentById(int id) {
        log.info("deleteStudentById() was called");
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new StudentWithSuchAnIdDoesNotExistException("Student with such an id does not exist and therefore can not be deleted. Method: deleteStudentByIdd() in StudentServiceImpl");
        }
    }

    public Class checkClassOfStudentTable() {
        log.info("checkClass() was called");
        return studentRepository.getClass();
    }
}
