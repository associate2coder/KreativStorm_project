package com.springbootproject.service;

import com.springbootproject.exception.ElementNotFoundException;
import com.springbootproject.exception.EnrollmentStatusException;
import com.springbootproject.object.Course;
import com.springbootproject.object.Student;
import com.springbootproject.repository.CourseRepository;
import com.springbootproject.repository.StudentRepository;
import org.junit.jupiter.api.*;
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
import static org.mockito.Mockito.when;

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

    @BeforeEach
    public void setTestingObjects() {
        enrolledStudent.setCourse(testCourse1);
        unenrolledStudent.setCourse(null);
    }

    // ENROLL METHOD

    @Test
    @DisplayName("#1.1: Non-existing student should throw exception")
    public void nonExistingStudentShouldThrowException1() {
        when(studentRepository.findById(anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(ElementNotFoundException.class, () -> enrolmentService.enroll(anyInt(), testCourse1.getId()));
    }

    @Test
    @DisplayName("#1.2: Non-existing course should throw exception")
    public void nonExistingCourseShouldThrowException1() {
        when(studentRepository.findById(anyInt())).thenReturn(Optional.of(unenrolledStudent));
        when(courseRepository.findById(anyInt())).thenReturn(Optional.empty());
       Assertions.assertThrows(ElementNotFoundException.class, () -> enrolmentService.enroll(unenrolledStudent.getId(), anyInt()));
    }

    @Test
    @DisplayName("#1.3: Enrolled student can't be re-enrolled")
    public void enrolledStudentCantBeEnrolledAgain() {
        int studentId = enrolledStudent.getId();
        int courseId = testCourse1.getId();
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(enrolledStudent));
        Assertions.assertThrows(EnrollmentStatusException.class, () -> enrolmentService.enroll(studentId, courseId));
    }

    @Test
    @DisplayName("#1.4: Correct enrolling student does not throw exception")
    public void correctStudentShouldNotThrowException1() {
        int studentId = unenrolledStudent.getId();
        int courseId = testCourse2.getId();
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(unenrolledStudent));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(testCourse2));
        Assertions.assertDoesNotThrow(() -> enrolmentService.enroll(studentId, courseId));
    }

    @Test
    @DisplayName("#1.5: Unenrolled existing student should enrol to existing course")
    public void unenrolledStudentDShouldEnrollSuccessfully() {
        int studentId = unenrolledStudent.getId();
        int courseId = testCourse2.getId();
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(unenrolledStudent));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(testCourse2));
        Student result = enrolmentService.enroll(studentId, courseId);
        Assertions.assertNotNull(result.getCourse(), "Student should have been enrolled, but he/she did not");
        Assertions.assertEquals(testCourse2, result.getCourse(), String.format("Student should have been enrolled to %s, but he/she enrolled to %s", testCourse2.getName(), result.getCourse().getName()));
    }


    // UNENROLL METHOD

    @Test
    @DisplayName("#2.1: Non-existing unenrolling student should throw exception")
    public void nonExistingStudentShouldThrowException2() {
       when(studentRepository.findById(anyInt())).thenReturn(Optional.empty());
       Assertions.assertThrows(ElementNotFoundException.class, () -> enrolmentService.unenroll(anyInt(), testCourse1.getId()));
    }

    @Test
    @DisplayName("#2.2: Non-existing course should throw exception")
    public void nonExistingCourseShouldThrowException2() {
        when(studentRepository.findById(anyInt())).thenReturn(Optional.of(unenrolledStudent));
        when(courseRepository.findById(anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(ElementNotFoundException.class, () -> enrolmentService.unenroll(unenrolledStudent.getId(), -1));
    }

    @Test
    @DisplayName("#2.3: Unenrolled student can't be unenrolled")
    public void unenrolledStudentCantBeUnenrolledAgain() {
        int studentId = unenrolledStudent.getId();
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(unenrolledStudent));
        when(courseRepository.findById(anyInt())).thenReturn(Optional.of(testCourse1)); // to ensure that 404 exception is not thrown
        Assertions.assertThrows(EnrollmentStatusException.class, () -> enrolmentService.unenroll(studentId, anyInt()));
    }

    @Test
    @DisplayName("#2.4: Wrong course to unenroll")
    public void wrongCourseToUnenroll() {
        int studentId = enrolledStudent.getId(); // enrolled in testCourse1
        int courseId = testCourse2.getId(); // testCourse2
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(enrolledStudent));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(testCourse2)); // to ensure that 404 exception is not thrown
        Assertions.assertThrows(EnrollmentStatusException.class, () -> enrolmentService.unenroll(studentId, courseId));
    }

    @Test
    @DisplayName("#2.5: Correct unenrolling student does not throw exception")
    public void correctStudentShouldNotThrowException2() {
        int studentId = enrolledStudent.getId(); // enrolled in testCourse1
        int courseId = enrolledStudent.getCourse().getId(); // testCourse1

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(enrolledStudent));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(enrolledStudent.getCourse()));
        when(studentRepository.save(any(Student.class))).thenAnswer(i -> i.getArgument(0));
        Assertions.assertDoesNotThrow(() -> enrolmentService.unenroll(studentId, courseId));
    }
}
