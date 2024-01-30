package com.springbootproject.controller;

import com.springbootproject.dto.IdDto;
import com.springbootproject.dto.StudentDto;
import com.springbootproject.dto.StudentListDto;
import com.springbootproject.object.Student;
import com.springbootproject.service.StudentService;
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

    @Autowired
    StudentService studentService;

    /*@RestController version
    @GetMapping("/studentTest")
    public String studentTest() {
        log.info("@RestController studentTest() was called");
        return "Hello World";
    }
     */

    //@Controller version
    @GetMapping("/")
    public String studentTest(Model model) {
        log.info("@Controller studentTest() was called");
        model.addAttribute("helloworld", "Hello World");
        return "index";
    }

    /*@RestController version
    @PostMapping("/addnewstudent")
    public Student addNewItem(@RequestBody Student student) throws NullPointerException {
        log.info("@RestController addNewItem() was called");
        if (student != null) {
            return studentService.save(student);
        } else {
            throw new NullPointerException("it's null in TestController/addNewItem()");
        }
    }
     */

    //@Controller version:
    @GetMapping ("/addnewstudentform")
    public ModelAndView addNewStudentForm(){
        log.info("@Controller addNewItemForm() was called");
        ModelAndView mav = new ModelAndView("addnew");
        Student student = new Student();
        mav.addObject("student", student);
        return mav;
    }

    @PostMapping("/addnewstudent")
    public String addNewStudent(@ModelAttribute StudentDto studentDto) {
        log.info("@Controller addNewItem() was called");
        if (studentDto != null) {
            studentService.saveStudent(studentDto);
            return "redirect:/";
        } else {
            throw new NullPointerException("It is null in TestController/addNewItem()");
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
        return studentService.saveMultipleAtOnce(students);
    }
    */

    //@Controller version:
    @GetMapping("/createstudentlist")
    public String showStudentListForm(Model model) {
        StudentListDto testObjectListDto = new StudentListDto();

        for (int i = 1; i <= 3; i++) {
            testObjectListDto.addTestObjectToList(new Student());
        }

        model.addAttribute("testObjectDto", testObjectListDto);
        return "addnewlist";
    }

    @GetMapping ("/addnewstudentlist")
    public ModelAndView addNewStudentListForm(){
        log.info("@Controller addNewListForm() was called");
        ModelAndView mav = new ModelAndView("addnewlist");
        Student student = new Student();
        mav.addObject("testObject", student);
        return mav;
    }

    @PostMapping("/addNewListTest")
    public String addNewStudentList(@ModelAttribute StudentDto studentDto) {
        log.info("@Controller addNewList() was called");
        if (studentDto != null) {
            studentService.saveStudent(studentDto);
            return "redirect:/";
        } else {
            throw new NullPointerException("It is null in TestController/addNewList()");
        }
    }

    /*@RestController version:
    @PutMapping("/updateexistingstudent")
    public Student updateExistingStudent(@RequestBody StudentDto studentDto) throws Exception {
        return studentService.updateStudent(studentDto);
    }
     */

    //@Controller version: mot yet
    @PutMapping("/updateexistingstudent")
    public Student updateExistingStudent(@RequestBody StudentDto studentDto) throws Exception {
        return studentService.updateStudent(studentDto);
    }


    /*@RestController version:
    @PostMapping("/checkifstudentexistsbyid")
    public boolean checkIfStudentExistsById(@RequestBody IdDto id) {
        log.info("checkIfStudentExistsById() was called");
        return studentService.checkIfStudentExistsById(id.getId());
    }
     */

    //@Controller version
    @PostMapping("/checkifstudentexistsbyid")
    public boolean checkIfStudentExistsById(@RequestBody IdDto id) {
        log.info("checkIfStudentExistsById() was called");
        return studentService.checkIfStudentExistsById(id.getId());
    }

    /*@RestController version:
    @PostMapping("/findstudentbyid")
    public Optional<Student> findStudentById(@RequestBody IdDto id) {
        log.info("findStudentById() was called");
        return studentService.findStudentById(id.getId());
    }
     */

    //@Controller version
    @PostMapping("/findstudentbyid")
    public Optional<Student> findStudentById(@RequestBody IdDto id) {
        log.info("findStudentById() was called");
        return studentService.findStudentById(id.getId());
    }

    /*@RestController version:
    @PostMapping("/findallstudents")
    public List<Student> findAllStudents() {
        log.info("@RestController findAllStudents() was called");
        return studentService.findAllStudents();
    }
     */

    //@Controller version
    @GetMapping("/findall")
    public String findAllStudents(Model model) {
        log.info("@Controller findAllStudents() was called");
        model.addAttribute("testObjectList", studentService.findAllStudents());
        return "findall";
    }

    /*@RestController version
    @PostMapping("/countalltherowsinthestudenttable")
    public long countAllTheRowsInTheStudentTable() {
        log.info("@RestController countAllTheRowsInTheStudentTable() was called");
        return studentService.countAllTheRowsInTheStudentTable();
    }
     */

    //@Controller version:
    @GetMapping("/countalltherows")
    public String countAllTheRowsInTheStudentTable(Model model) {
        log.info("@Controller countAllTheRowsInTheStudentTable() was called");
        model.addAttribute("numberOfRows", studentService.countAllTheStudentTableRows());
        return "countalltherows";
    }

    /*@RestController version:
    @DeleteMapping("/deletestudent/{id}")
    public void deleteStudentById(@PathVariable int id) {
        log.info("deleteStudentById() was called");
        studentService.deleteStudentById(id);
    }
     */

    //@Controller
    @DeleteMapping("/deletestudent/{id}")
    public void deleteStudentById(@PathVariable int id) {
        log.info("deleteStudentById() was called");
        studentService.deleteStudentById(id);
    }

    /*@RestController version:
    @PostMapping("/checkclassofstudent")
    public Class checkClass() {
        log.info("@RestController checkClass() was called");
        return studentService.checkClass();
    }
     */

    //@Controller version
    @GetMapping("/checkclassofstudents")
    public Class checkClassOfStudents(Model model) {
        log.info("@Controller checkClass() was called");
        model.addAttribute("className", studentService.checkClassOfStudentTable());
        return studentService.checkClassOfStudentTable();
    }
}
