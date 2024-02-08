package com.springbootproject.controller;

import com.springbootproject.dto.StudentDto;
import com.springbootproject.dto.StudentListDto;
import com.springbootproject.exception.StudentWithSuchAnIdDoesNotExistException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface StudentController {

//    String studentTest(Model model);

    String addNewStudentFormDisplay(Model model);

    String showStudentListForm(Model model);

    String addNewStudentAction(@ModelAttribute StudentDto studentDto, Model mode) throws NullPointerException;

    String addNewStudentListAction(@ModelAttribute StudentListDto studentListDto, Model model);

    String updateExistingStudent(@PathVariable int id, Model model) throws StudentWithSuchAnIdDoesNotExistException;

    String updateExistingStudentForm(@RequestBody StudentDto studentDto);

    boolean checkIfStudentExistsById(int id);

    String findStudentById(@RequestBody int id, Model model);

    String findAllStudents(Model model);

    String countAllTheRowsInTheStudentTable(Model model);

    String checkClassOfStudentTable(Model model);
}
