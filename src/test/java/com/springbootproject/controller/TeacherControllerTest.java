package com.springbootproject.controller;

import com.springbootproject.object.Teacher;
import com.springbootproject.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(TeacherController.class)
class TeacherControllerTest {
    @MockBean
    private TeacherService teacherService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void TeacherController_listTeachers_returnsTeachers() throws Exception {
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(new Teacher(1, "Jon", "jon@mail.com"));
        teacherList.add(new Teacher(2, "Jane", "jane@mail.com"));

        when(teacherService.getAllTeachers()).thenReturn(teacherList);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/teachers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("teacher/teachers"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("teachers"));
    }
}