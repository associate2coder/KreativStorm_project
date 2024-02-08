package com.springbootproject.repository;

import com.springbootproject.object.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void TeacherRepository_Save_ReturnSavedTeacher() {
        //TODO make it work
//        Teacher teacher = Teacher.builder()
//                .id(1L).name("Jon Doe").email("jon@mail.com")
//                .build();
//
//        Teacher savedTeacher = teacherRepository.save(teacher);
//
//        Assertions.assertNotNull(savedTeacher);
    }
}