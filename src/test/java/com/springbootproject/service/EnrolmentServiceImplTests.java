package com.springbootproject.service;

import com.springbootproject.exception.ElementNotFoundException;
import com.springbootproject.exception.EnrollmentStatusException;
import com.springbootproject.object.Course;
import com.springbootproject.object.Student;
import com.springbootproject.repository.CourseRepository;
import com.springbootproject.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class EnrolmentServiceImplTests {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private EnrolmentServiceImpl enrolmentService;



    private static Course testCourse1;
    private static Course testCourse2;
    private static Student enrolledStudent;
    private static Student unenrolledStudent;

    @BeforeAll
    public static void init() {
        // setting a test course 1
        testCourse1 = new Course();
        testCourse1.setId(1);
        testCourse1.setName("Java for beginners");
        testCourse1.setTeacher("Teacher #1 name");

        // setting a test course 2
        testCourse2 = new Course();
        testCourse2.setId(2);
        testCourse2.setName("Docker for DevOps specialists");
        testCourse2.setTeacher("Teacher #4 name");

        // setting a test enrolled Student
        enrolledStudent = new Student();
        enrolledStudent.setId(1);
        enrolledStudent.setName("John Smith");
        enrolledStudent.setAge(20);
        enrolledStudent.setEmail("jsmith@gmail.com");
        enrolledStudent.setCourse(testCourse1);

        // setting a test unenrolled Student
        unenrolledStudent = new Student();
        unenrolledStudent.setId(2);
        unenrolledStudent.setName("Veronica Adams");
        unenrolledStudent.setAge(18);
        unenrolledStudent.setEmail("vadams@gmail.com");
    }


    @Test
    @DisplayName("#1: Non-existing student should throw exception")
    public void nonExistingStudentShouldThrowException() {
        Mockito.lenient().when(studentRepository.findById(anyInt())).thenReturn(Optional.empty()); // IDE says that this line is not used. I do not understand the reason behind it yet
        Assertions.assertThrows(ElementNotFoundException.class, () -> enrolmentService.enroll(anyInt(), 1));
    }

    @Test
    @DisplayName("#2: Non-existing course should throw exception")
    public void nonExistingCourseShouldThrowException() {
        Mockito.lenient().when(courseRepository.findById(anyInt())).thenReturn(Optional.empty()); // IDE says that this line is not used. I do not understand the reason behind it yet
        Assertions.assertThrows(ElementNotFoundException.class, () -> enrolmentService.enroll(unenrolledStudent.getId(), -1));
    }

    @Test
    @DisplayName("#3: Enrolled student can't be re-enrolled")
    public void enrolledStudentCantBeEnrolledAgain() {
        Mockito.when(studentRepository.findById(anyInt())).thenReturn(Optional.of(enrolledStudent));
        Assertions.assertThrows(EnrollmentStatusException.class, () -> enrolmentService.enroll(enrolledStudent.getId(), testCourse2.getId()));
    }

    @Test
    @DisplayName("#4: Unenrolled existing student should enrol to existing course")
    public void unenrolledStudentDShouldEnrollSuccessfully() {
        Mockito.when(studentRepository.findById(unenrolledStudent.getId())).thenReturn(Optional.of(unenrolledStudent));
        Mockito.when(courseRepository.findById(testCourse2.getId())).thenReturn(Optional.of(testCourse2));
        Student result = enrolmentService.enroll(unenrolledStudent.getId(), testCourse2.getId());
        Assertions.assertNotNull(result.getCourse(), "Student has not been enrolled");
        Assertions.assertEquals(testCourse2, result.getCourse(), "Student was not enrolled to a correct course");
    }
}
