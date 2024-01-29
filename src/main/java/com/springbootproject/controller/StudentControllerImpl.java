package com.springbootproject.controller;

import com.springbootproject.dao.IdDto;
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
    @GetMapping("/test")
    public String test() {
        log.info("@RestController test() was called");
        return "Hello World";
    }
     */

    //@Controller version
    @GetMapping("/")
    public String test(Model model) {
        log.info("@Controller test() was called");
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
            studentService.save(studentDto);
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
            studentService.save(studentDto);
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
    public boolean checkIfItExistsById(@RequestBody IdDto id) {
        log.info("checkIfItExistsById() was called");
        return studentService.checkIfItExistsById(id.getId());
    }

     */

    //@Controller version
    @PostMapping("/checkifstudentexistsbyid")
    public boolean checkIfItExistsById(@RequestBody IdDto id) {
        log.info("checkIfItExistsById() was called");
        return studentService.checkIfItExistsById(id.getId());
    }


    /*@RestController version:
    @PostMapping("/findstudentbyid")
    public Optional<Student> findById(@RequestBody IdDto id) {
        log.info("findById() was called");
        return studentService.findById(id.getId());
    }
     */

    //@Controller version
    @PostMapping("/findstudentbyid")
    public Optional<Student> findById(@RequestBody IdDto id) {
        log.info("findById() was called");
        return studentService.findById(id.getId());
    }

    /*@RestController version:
    @PostMapping("/findallstudents")
    public List<Student> findAll() {
        log.info("@RestController findAll() was called");
        return studentService.findAll();
    }
     */

    //@Controller version
    @GetMapping("/findall")
    public String findAll(Model model) {
        log.info("@Controller findAll() was called");
        model.addAttribute("testObjectList", studentService.findAll());
        return "findall";
    }

    /*@RestController version
    @PostMapping("/countalltherowsinthestudenttable")
    public long countAllTheRows() {
        log.info("@RestController countAllTheRows() was called");
        return studentService.countAllTheRows();
    }
     */

    //@Controller version:
    @GetMapping("/countalltherows")
    public String countAllTheRows(Model model) {
        log.info("@Controller countAllTheRows() was called");
        model.addAttribute("numberOfRows", studentService.countAllTheRows());
        return "countalltherows";
    }

    /*@RestController version:
    @DeleteMapping("/deletestudent/{id}")
    public void deleteById(@PathVariable int id) {
        log.info("deleteById() was called");
        studentService.deleteById(id);
    }
     */

    //@Controller
    @DeleteMapping("/deletestudent/{id}")
    public void deleteById(@PathVariable int id) {
        log.info("deleteById() was called");
        studentService.deleteById(id);
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
        model.addAttribute("className", studentService.checkClass());
        return studentService.checkClass();
    }
}