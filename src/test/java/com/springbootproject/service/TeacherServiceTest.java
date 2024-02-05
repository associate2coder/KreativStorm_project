package com.springbootproject.service;

import com.springbootproject.dto.TeacherDto;
import com.springbootproject.exception.TeacherWithSuchAnIdDoesNotExistException;
import com.springbootproject.object.Teacher;
import com.springbootproject.repository.TeacherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;
    @InjectMocks
    private TeacherServiceImpl teacherService;

    @Test
    public void TeacherService_saveTeacher_SaveTeacherReturnsOneTeacher() {
        Teacher expected = new Teacher(1L, "Juan",
                "juan@mail.com");

        Mockito.when(teacherRepository.save(new Teacher(1L, "Juan", "juan@mail.com")))
                .thenReturn(expected);

        Teacher result = teacherService.saveTeacher(
                new TeacherDto(1L, "Juan", "juan@mail.com")
                );

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void TeacherService_getTeacherById_returnsTeacher() {

        Teacher teacher = new Teacher(1L, "Jon", "jon@mail.com");
        teacherRepository.save(teacher);

        Mockito.when(teacherRepository.findById(1L))
                        .thenReturn(Optional.of(new Teacher(1L, "Jon", "jon@mail.com")));

        assertEquals(teacher, teacherService.getTeacherById(1L));
    }

    @Test
    public void TeacherService_getTeacherById_nullIdThrowsException() {

        assertThrows(TeacherWithSuchAnIdDoesNotExistException.class,
                () -> teacherService.getTeacherById(null));

    }

}