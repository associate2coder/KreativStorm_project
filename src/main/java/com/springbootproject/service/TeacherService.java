package com.springbootproject.service;

import com.springbootproject.dto.TeacherDto;
import com.springbootproject.object.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    Teacher saveTeacher(TeacherDto teacherDto);
    Teacher getTeacherById(int id);
    Teacher updateTeacher(Teacher teacher);
    void deleteTeacherById(int id);
}
