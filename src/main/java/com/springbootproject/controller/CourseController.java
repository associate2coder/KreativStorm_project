package com.springbootproject.controller;

import com.springbootproject.exception.ElementNotFoundException;
import com.springbootproject.object.Course;
import com.springbootproject.object.Student;
import com.springbootproject.service.AdminService;
import com.springbootproject.service.EnrolmentService;
import com.springbootproject.utils.DataFormatUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/course")
public class CourseController {

    private final AdminService courseService;
    private final EnrolmentService enrolmentService;

    @GetMapping("/select-student")
    public String getExistingCourses(@RequestParam String selectedStudent, Model model) {
        // TODO open issue - path variable "studentId" should be retrieved from elsewhere (e.g. username used for login purposes)
        log.trace("Request has been made to get all existing courses");

        List<Course> courses = courseService.findAllCourses();
        model.addAttribute("student", selectedStudent); // TODO to confirm if needed
        model.addAttribute("courses", courses);

        log.warn("{} courses found. Course selection template will be uploaded", courses.size());
        return "enrollment/course-selection";
    }

    @GetMapping("/{studentId}/{courseId}")
    public String getCourseProfile(@PathVariable String studentId, @PathVariable String courseId, Model model) {
        // TODO open issue - path variable "studentId" should be retrieved from elsewhere (e.g. username used for login purposes)
        log.trace("Request has been made to open information on course with id {}", courseId);

        int courseIdNumber = DataFormatUtils.parseStringIdNumber(courseId);

        try {
            Course course = courseService.show(courseIdNumber);

            model.addAttribute("student", studentId); // TODO to confirm if needed
            model.addAttribute("course", course);

            log.debug("Information on course {} retrieved.", course.getName());
            return "enrollment/course-profile";
        } catch (NullPointerException e) { // is not checked at the AdminService level
            throw new ElementNotFoundException(e.getMessage());
        }
    }

    @PutMapping("/{studentId}/{courseId}/enroll")
    public String enrollStudent(@PathVariable String studentId, @PathVariable String courseId, Model model) {
        // TODO open issue - path variable "studentId" should be retrieved from elsewhere (e.g. username used for login purposes)
        log.trace("Request has been made to to enroll student with the course with id {}", courseId);

        int studentIdNumber = DataFormatUtils.parseStringIdNumber(studentId);
        int courseIdNumber = DataFormatUtils.parseStringIdNumber(courseId);

        try {

            Course course = courseService.show(courseIdNumber); // TODO to adjust once CourseService interface is ready
            Student student = enrolmentService.enroll(studentIdNumber, courseIdNumber);

            model.addAttribute("student", student);
            model.addAttribute("course", course);

            return "enrollment/enroll-confirmation";
        } catch (NullPointerException e) { // is not checked at the AdminService level
            throw new ElementNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/{studentId}/{courseId}/unenroll")
    public String unenrollStudent(@PathVariable String studentId, @PathVariable String courseId, Model model) {
        // TODO open issue - path variable "studentId" should be retrieved from elsewhere (e.g. username used for login purposes)
        log.trace("Request has been made to to unenroll student from the course with id {}", courseId);

        int studentIdNumber = DataFormatUtils.parseStringIdNumber(studentId);
        int courseIdNumber = DataFormatUtils.parseStringIdNumber(courseId);

        try {
            Course course = courseService.show(courseIdNumber);
            Student student = enrolmentService.unenroll(studentIdNumber, courseIdNumber);

            model.addAttribute("student", student);
            model.addAttribute("course", course);
            return "enrollment/unenroll-confirmation";
        } catch (NullPointerException e) { // is not checked at the AdminService level
            throw new ElementNotFoundException(e.getMessage());
        }
    }
}
