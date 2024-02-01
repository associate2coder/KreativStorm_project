package com.springbootproject.service;

import com.springbootproject.exception.ElementNotFoundException;
import com.springbootproject.object.Course;
import com.springbootproject.object.Student;
import com.springbootproject.exception.EnrollmentStatusException;
import com.springbootproject.repository.CourseRepository;
import com.springbootproject.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrolmentServiceImpl implements EnrolmentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


    // TODO Delete (to be used for testing only)
//    @PostConstruct
//    public void init() {
//        Student student1 = new Student();
//        student1.setName("John Smith");
//        student1.setAge(20);
//        student1.setEmail("jsmith@gmail.com");
//        studentRepository.save(student1);
//
//        Student student2 = new Student();
//        student2.setName("Veronica Adams");
//        student2.setAge(18);
//        student2.setEmail("vadams@gmail.com");
//        studentRepository.save(student2);
//
//        Course course1 = new Course();
//        course1.setName("Java for beginners");
//        course1.setTeacher("Teacher #1 name");
//        courseRepository.save(course1);
//
//        Course course2 = new Course();
//        course2.setName("Spring Boot for beginners");
//        course2.setTeacher("Teacher #2 name");
//        courseRepository.save(course2);
//
//        Course course3 = new Course();
//        course3.setName("Advance Java for developers");
//        course3.setTeacher("Teacher #3 name");
//        courseRepository.save(course3);
//
//        Course course4 = new Course();
//        course4.setName("Docker for DevOps specialists");
//        course4.setTeacher("Teacher #4 name");
//        courseRepository.save(course4);
//    }


    @Override
    public Student enroll(int studentId, int courseId) {
        log.trace("starting the process of enrollment of student with id {} to course with id {}", studentId, courseId);
        // student record is retrieved from the database.
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ElementNotFoundException("Student not found with id " + studentId));

        // check if student has already been enrolled in any course
        if (student.getCourse() != null) {
            String message = String.format("Student %s already enrolled in the course %s", student.getName(), student.getCourse().getName());
            throw new EnrollmentStatusException(message);
        }

        // desired course is retrieved from the database
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ElementNotFoundException("Course not found with id " + courseId));

        // check if desired course has vacant seats
        // TODO waiting for capacity field
//        if (course.getStudentList().size() >= course.getCapacity()) {
//            String message = String.format("Unable to enrol as the course %s is full. Please choose another course.", course.getName());
//            throw new EnrollmentStatusException(message);
//        }

        // update the student record
        student.setCourse(course);
        studentRepository.save(student);
        log.debug("Student {} (id {}) successfully enrolled in the course {} (id {})", student.getName(), student.getId(), course.getName(), course.getId());
        return student;
    }

    @Override
    public Student unenroll(int studentId, int courseId) {
        log.trace("starting the process of unenrollment of student with id {} to course with id {}", studentId, courseId);

        // student record is retrieved from the database.
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ElementNotFoundException("Student not found with id " + studentId));

        // desired course is retrieved from the database
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ElementNotFoundException("Course not found with id " + courseId));

        // nothing is changed if student is not actually enrolled in any course
        if (student.getCourse() == null) {
            String message = String.format("Student %s is not currently enrolled in any of the courses", student.getName());
            throw new EnrollmentStatusException(message);
            // nothing is changed if student is not enrolled to the course, from which he needs to be enenrolled
        } else if (student.getCourse() != course) {
            String message = String.format("Student %s is not currently enrolled in the course %s", student.getName(), course.getName());
            throw new EnrollmentStatusException(message);
            // otherwise, student is enenrolled
        } else {
            student.setCourse(null);
            student = studentRepository.save(student);
            log.debug("Student {} (id {}) successfully unenrolled from the course {} (id {})", student.getName(), student.getId(), course.getName(), course.getId());
            return student;
        }
    }
}
