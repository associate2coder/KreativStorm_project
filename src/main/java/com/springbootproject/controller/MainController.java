package com.springbootproject.controller;

import com.springbootproject.object.Student;
import com.springbootproject.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final StudentService studentService;

    @GetMapping("/")
    public String index(Model model) {
        log.trace("Request has been made to load index page");

        List<Student> students = studentService.findAllStudents().stream()
                .filter(student -> student.getCourse() == null)
                .toList();
        model.addAttribute("students", students);
        return "index";
    }

}
