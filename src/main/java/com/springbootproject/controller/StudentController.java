package com.springbootproject.controller;

import com.springbootproject.dto.StudentDto;
import com.springbootproject.object.Student;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface StudentController {
    String studentTest(Model model);

    String addNewStudentFormDisplay(Model model);

    String showStudentListForm(Model model);

    String addNewStudentAction(@ModelAttribute StudentDto studentDto, Model mode) throws NullPointerException;

    String addNewStudentList(@ModelAttribute StudentDto studentDto);

    Student updateExistingStudent(@RequestBody StudentDto studentDto) throws Exception;

    boolean checkIfStudentExistsById(@RequestBody int id);

    String findStudentById(@RequestBody int id, Model model);

    String findAllStudents(Model model);

    String countAllTheRowsInTheStudentTable(Model model);

    Class checkClassOfStudents(Model model);
}
