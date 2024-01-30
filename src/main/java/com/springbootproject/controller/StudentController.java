package com.springbootproject.controller;

import com.springbootproject.dto.IdDto;
import com.springbootproject.dto.StudentDto;
import com.springbootproject.object.Student;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

public interface StudentController {
    String studentTest(Model model);

    String addNewStudentFormDisplay(Model model);

    String showStudentListForm(Model model);

    String addNewStudentAction(@ModelAttribute StudentDto studentDto) throws NullPointerException;

    String addNewStudentList(@ModelAttribute StudentDto studentDto);

    Student updateExistingStudent(@RequestBody StudentDto studentDto) throws Exception;

    boolean checkIfStudentExistsById(@RequestBody IdDto id);

    Optional<Student> findStudentById(@RequestBody IdDto id);

    String findAllStudents(Model model);

    String countAllTheRowsInTheStudentTable(Model model);

    void deleteStudentById(@PathVariable int id);

    Class checkClassOfStudents(Model model);
}
