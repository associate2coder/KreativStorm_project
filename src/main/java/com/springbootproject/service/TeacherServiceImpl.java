package com.springbootproject.service;

import com.springbootproject.dto.TeacherDto;
import com.springbootproject.exception.TeacherWithSuchAnIdDoesNotExistException;
import com.springbootproject.object.Teacher;
import com.springbootproject.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
    public Teacher saveTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherDto.getId());
        teacher.setName(teacherDto.getName());
        teacher.setEmail(teacherDto.getEmail());
        try {
        return teacherRepository.save(teacher);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error saving teacher." + e.getMessage(), e);
        }
    }

    public Teacher getTeacherById(int id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherWithSuchAnIdDoesNotExistException(
                        "Teacher with such an Id does not exist."
                ));
        return teacher;
    }

    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public void deleteTeacherById(int id) {
        teacherRepository.deleteById(id);
    }
}
