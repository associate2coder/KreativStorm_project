package com.springbootproject.controller;

import com.springbootproject.dto.IdDto;
import com.springbootproject.dto.StudentDto;
import com.springbootproject.dto.StudentDtoListDto;
import com.springbootproject.exception.student.StudentDtoListDtoInputException;
import com.springbootproject.exception.student.StudentDtoNullException;
import com.springbootproject.exception.student.StudentWithSuchAnIdDoesNotExistException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface StudentController {

//    String studentTest(Model model);

    String addNewStudentFormDisplay(Model model);

    String addNewStudentAction(@ModelAttribute StudentDto studentDto, Model mode) throws StudentDtoNullException;

    String addStudentDtoListPage(Model model);

    boolean testIfStudentListDtoInputIsCorrect(StudentDtoListDto studentDtoListDto) throws StudentDtoListDtoInputException;

    String addNewStudentListFormAction(@ModelAttribute StudentDtoListDto studentDtoList, Model model) throws StudentDtoListDtoInputException;

    String updateExistingStudent(@PathVariable int id, Model model) throws StudentWithSuchAnIdDoesNotExistException;

    String updateExistingStudentForm(@RequestBody StudentDto studentDto);

    boolean checkIfStudentExistsById(int id);

    String findStudentById(@RequestBody int id, Model model) throws  StudentWithSuchAnIdDoesNotExistException;

    String findAllStudents(Model model);

    String deleteStudentById(@ModelAttribute IdDto idOfStudentToBeDeleted, Model model);

    String countAllTheRowsInTheStudentTable(Model model);

    String checkClassOfStudentTable(Model model);
}
