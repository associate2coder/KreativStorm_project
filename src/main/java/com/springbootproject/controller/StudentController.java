package com.springbootproject.controller;

import com.springbootproject.dao.IdDto;
import com.springbootproject.dto.StudentDto;
import com.springbootproject.object.Student;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

public interface StudentController {
    String test(Model model);

    ModelAndView addNewStudentForm();

    String addNewStudent(@ModelAttribute StudentDto studentdto);

    String showStudentListForm(Model model);

    ModelAndView addNewStudentListForm();

    String addNewStudentList(@ModelAttribute StudentDto studentDto);

    Student updateExistingStudent(@RequestBody StudentDto studentDto) throws Exception;

    boolean checkIfItExistsById(@RequestBody IdDto id);

    Optional<Student> findById(@RequestBody IdDto id);

    String findAll(Model model);

    String countAllTheRows(Model model);

    void deleteById(@PathVariable int id);

    Class checkClassOfStudents(Model model);

    }
