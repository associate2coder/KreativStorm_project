package com.springbootproject.controller;

import com.springbootproject.dto.IdDto;
import com.springbootproject.dto.StudentDto;
import com.springbootproject.dto.StudentListDto;
import com.springbootproject.object.Student;
import com.springbootproject.service.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

//RestController for now, will Controller later when we do front-end

@Controller
@Slf4j
public class StudentControllerImpl implements StudentController {
//
//    Task:
//    Student controller. Endpoints: SIMON
//    GET (“/student/register”) - returns student registration form template (template #2)
//    POST (“/student/register”) - sends student information to backend, returns student registered message (template #9).
//    GET (“student/{studentId}”) - sends studentId (path variable) to backend to retrieve student info by student id. Returns student profile template (template #3)

//    Main view
//    Student registration form (student enters information and presses submit button)
//    Student profile view (student can delete his/hers profile - delete profile button)
//    Course registration form (admin enters course information and presses submit button)
//    Course selection view (students looks for courses)
//    Course page view (provides information the selected course and enroll/unenroll button)
//    Admin view (from here admin can add new courses or update/delete existing)
//
//    Technical views:
//    Course registered view (to show course registration status message)
//    Student registered view (to show student registration/profile deletion status message)
//    Error view (view shown when error occurs)
//    Student enrolled view (to show that student enrolled)


    @Autowired
    StudentServiceImpl studentServiceImpl;

    /*@RestController version
    @GetMapping("/studentTest")
    public String studentTest() {
        log.info("@RestController studentTest() was called");
        return "Hello World";
    }
     */

    //@Controller version
//    @GetMapping("/")
//    public String studentTest(Model model) {
//        log.info("@Controller studentTest() was called");
//        model.addAttribute("helloworld", "Hello World");
//        return "index";
//    }

    /*@RestController version
    @PostMapping("/addnewstudent")
    public Student addNewItem(@RequestBody Student student) throws NullPointerException {
        log.info("@RestController addNewItem() was called");
        if (student != null) {
            return studentServiceImpl.save(student);
        } else {
            throw new NullPointerException("it's null in TestController/addNewItem()");
        }
    }
     */

    //@Controller version:
    @GetMapping("student/register")
    public String addNewStudentFormDisplay(Model model) {
        log.info("@Controller addNewStudentForm() was called");
        model.addAttribute("studentDto", new StudentDto());
        return "student/register";
    }

    @PostMapping("student/addnewstudentsubmit")
    public String addNewStudentAction(@ModelAttribute StudentDto studentDto, Model model) throws NullPointerException {
        log.info("@Controller addNewStudent() was called");
        if (studentDto != null) {
            studentServiceImpl.saveStudent(studentDto);
            model.addAttribute("successfulRegistration", true);
            model.addAttribute("successfulRegistrationMessage", "Student registered successfully.");
            return "student/register";
        } else {
            model.addAttribute("successfulRegistration", true);
            model.addAttribute("failureAtRegistrationMessage", "Student registration failed.");
            throw new NullPointerException("It is null in TestController/addNewStudent()");
        }
    }


    /*@RestController version:
    @PostMapping("/addmultiplenewstudentsatonce")
    public List addMultipleNewItemsAtOnce(@RequestBody List<Student> students) throws Exception {
        log.info("addMultipleNewItemsAtOnce() was called");
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i) == null) {
                throw new Exception("There is a null in TestController/addmultiplenewitemsatonce()");
            }
        }
        return studentServiceImpl.saveMultipleStudentsAtOnce(students);
    }
    */

    //@Controller version:
    @GetMapping("student/addnewstudentlist")
    public String showStudentListForm(Model model) {
        StudentListDto studentListDto = new StudentListDto();

        for (int i = 1; i <= 10; i++) {
            studentListDto.addStudentToList(new StudentDto(1,"test",1,"test@test.com"));
        }

        model.addAttribute("studentListDto", studentListDto);
        return "student/addnewstudentlist";
    }

//    @GetMapping("/addnewstudentlist")
//    public ModelAndView addNewStudentListForm() {
//        log.info("@Controller addNewListForm() was called");
//        ModelAndView mav = new ModelAndView("addnewlist");
//        Student student = new Student();
//        mav.addObject("testObject", student);
//        return mav;
//    }

    @PostMapping("student/addnewstudentlist")
    public String addNewStudentList(@ModelAttribute StudentDto studentDto, Model model) {
        log.info("@Controller addNewStudentList( was called");
        if (studentDto != null) {
            studentServiceImpl.saveStudent(studentDto);
            return "redirect:/";
        } else {
            throw new NullPointerException("It is null in TestController/addNewStudentList()");
        }
    }


    /*@RestController version:
    @PutMapping("/updateexistingstudent")
    public Student updateExistingStudent(@RequestBody StudentDto studentDto) throws Exception {
        return studentServiceImpl.updateStudent(studentDto);
    }
     */

    //@Controller version: mot yet
    @PutMapping("/updateexistingstudent")
    public Student updateExistingStudent(@RequestBody StudentDto studentDto) {
        return studentServiceImpl.updateStudent(studentDto);
    }

    /*@RestController version:
    @PostMapping("/checkifstudentexistsbyid")
    public boolean checkIfStudentExistsById(@RequestBody IdDto id) {
        log.info("checkIfStudentExistsById() was called");
        return studentServiceImpl.checkIfStudentExistsById(id.getId());
    }
     */

    //@Controller version
    @PostMapping("/checkifstudentexistsbyid")
    public boolean checkIfStudentExistsById(@RequestBody int id) {
        log.info("checkIfStudentExistsById() was called");
        return studentServiceImpl.checkIfStudentExistsById(id);
    }

    /*@RestController version:
    @PostMapping("/findstudentbyid")
    public Optional<Student> findStudentById(@RequestBody IdDto id) {
        log.info("findStudentById() was called");
        return studentServiceImpl.findStudentById(id.getId());
    }
     */

    //@Controller version
    @GetMapping("student/{id}")
    public String findStudentById(@PathVariable int id, Model model) {
        log.info("findStudentById() was called");
        StudentDto studentDto = new StudentDto();
        Student student;

        if (studentServiceImpl.findStudentById(id).isPresent()) {
            Optional<Student> studentOptional = studentServiceImpl.findStudentById(id);
            student = studentOptional.orElse(new Student(1, "No name", 1, "no@no.com"));
            studentDto.setId(student.getId());
            studentDto.setName(student.getName());
            studentDto.setAge(student.getAge());
            studentDto.setEmail(student.getEmail());
        }

        model.addAttribute("studentDto", studentDto);
        //
        log.info("@Controller deleteStudentForm() was called");
        IdDto idDto = new IdDto();
        model.addAttribute("idOfStudentToBeDeleted", idDto);
        return "student/studentinfo";
    }



    /*@RestController version:
    @PostMapping("/findallstudents")
    public List<Student> findAllStudents() {
        log.info("@RestController findAllStudents() was called");
        return studentServiceImpl.findAllStudents();
    }
     */

    //@Controller version
    @GetMapping("student/listallstudents")
    public String findAllStudents(Model model) {
        log.info("@Controller findAllStudents() was called");
        model.addAttribute("listOfAllStudents", studentServiceImpl.findAllStudents());
        return "student/listallstudents";
    }

    /*@RestController version
    @PostMapping("/countalltherowsinthestudenttable")
    public long countAllTheRowsInTheStudentTable() {
        log.info("@RestController countAllTheRowsInTheStudentTable() was called");
        return studentServiceImpl.countAllTheRowsInTheStudentTable();
    }
     */

    //@Controller version:
    @GetMapping("student/countallthestudenttablerows")
    public String countAllTheRowsInTheStudentTable(Model model) {
        log.info("@Controller countAllTheRowsInTheStudentTable() was called");
        model.addAttribute("numberOfRowsInTheStudentTable", studentServiceImpl.countAllTheStudentTableRows());
        return "student/countallthestudenttablerows";
    }

    /*@RestController version:
    @DeleteMapping("/deletestudent/{id}")
    public void deleteStudentById(@PathVariable int id) {
        log.info("deleteStudentById() was called");
        studentServiceImpl.deleteStudentById(id);
    }
     */

    //@Controller
//    @GetMapping("student/studentinfo")
//    public String deleteStudentByIdForm(Model model) {
//        log.info("@Controller deleteStudentForm() was called");
//        IdDto idDto = new IdDto();
//        model.addAttribute("idOfStudentToBeDeleted", idDto);
//        return "student/studentinfo";
//    }

    @PostMapping ("student/deletestudent")
    public String deleteStudentById(@ModelAttribute IdDto idOfStudentToBeDeleted, Model model) {
        log.info("@Controller deleteStudentById() was called");
        studentServiceImpl.deleteStudentById(idOfStudentToBeDeleted.getId());
        model.addAttribute("studentDeletedMessage", "Student with the id: " + idOfStudentToBeDeleted.getId() + " was deleted.");
        model.addAttribute("studentDeleted", true);
        model.addAttribute("idOfStudentToBeDeleted", new IdDto());
        return "student/studentinfo";
    }

    /*@RestController version:
    @PostMapping("/checkclassofstudent")
    public Class checkClass() {
        log.info("@RestController checkClass() was called");
        return studentServiceImpl.checkClass();
    }
     */

    //@Controller version
    @GetMapping("student/checkclassofstudents")
    public Class checkClassOfStudents(Model model) {
        log.info("@Controller checkClass() was called");
        model.addAttribute("className", studentServiceImpl.checkClassOfStudentTable());
        return studentServiceImpl.checkClassOfStudentTable();
    }
}
