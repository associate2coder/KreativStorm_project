package com.springbootproject.controller;

import com.springbootproject.dto.student.StudentDto;
import com.springbootproject.dto.student.StudentDtoListDto;
import com.springbootproject.dto.student.StudentIdDto;
import com.springbootproject.exception.student.StudentDtoListDtoInputException;
import com.springbootproject.exception.student.StudentDtoNullException;
import com.springbootproject.exception.student.StudentWithSuchAnIdDoesNotExistException;
import com.springbootproject.object.Student;
import com.springbootproject.service.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class StudentControllerImpl implements StudentController {

    @Autowired
    StudentServiceImpl studentServiceImpl;

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
    @GetMapping("/student/register")
    public String addNewStudentFormDisplay(Model model) {
        log.info("@Controller addNewStudentFormDisplay() was called");
        model.addAttribute("studentDto", new StudentDto());
        return "/student/register";
    }

    @PostMapping("student/addnewstudentsubmit")
    public String addNewStudentAction(@ModelAttribute StudentDto studentDto, Model model) throws StudentDtoNullException {
        log.info("@Controller addNewStudentAction() was called");
        if (studentDto != null) {
            studentServiceImpl.saveStudent(studentDto);
            model.addAttribute("successfulRegistration", true);
            model.addAttribute("successfulRegistrationMessage", "Student registered successfully.");
            return "student/register";
        } else {
            model.addAttribute("successfulRegistration", true);
            model.addAttribute("failureAtRegistrationMessage", "Student registration failed.");
            throw new StudentDtoNullException("StudentDto is null in StudentControllerImpl/addNewStudentAction()");
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
    @GetMapping("/student/addnewstudentlist")
    public String addStudentDtoListPage(Model model) {
        log.info("@Controller addStudentDtoListPage() was called");
        StudentDtoListDto studentDtoListDto = new StudentDtoListDto();
        for (int i = 1; i <= 3; i++) {
            studentDtoListDto.addStudentDto(new StudentDto());
        }
        model.addAttribute("studentDtoListDtoForm", studentDtoListDto);
        return "/student/addnewstudentlist";
    }

    public boolean testIfStudentListDtoInputIsCorrect(StudentDtoListDto studentDtoListDto) throws StudentDtoListDtoInputException {
        for (int i = 0; i < studentDtoListDto.getStudentDtoListDto().size(); i++) {
            StudentDto studentDto = studentDtoListDto.getStudentDtoListDto().get(i);
            if (studentDto.getName().isBlank() || studentDto.getName().isEmpty()) {
                throw new StudentDtoListDtoInputException("Student Name is wrong. Name field cannot be empty.");
            }
            if (studentDto.getAge() < 1) {
                throw new StudentDtoListDtoInputException("Student Age is wrong. Must be 1 year of age or older.");
            }
            if (studentDto.getEmail().isBlank() || studentDto.getEmail().isEmpty()) {
                throw new StudentDtoListDtoInputException("Student Email is wrong. Email field cannot be empty.");
            }
        }
        return true;
    }

    @PostMapping("/student/addnewstudentlistform")
    public String addNewStudentListFormAction(@ModelAttribute StudentDtoListDto studentDtoListDto, Model model) throws StudentDtoListDtoInputException {
        log.info("@Controller addNewStudentListFormAction() was called");
        testIfStudentListDtoInputIsCorrect(studentDtoListDto);
        studentServiceImpl.saveMultipleStudentsAtOnce(studentDtoListDto.getStudentDtoListDto());
        model.addAttribute("studentDtoListDto", studentServiceImpl.findAllStudentsButReturnAsStudentDtoList());
        return "redirect:/student/listallstudents";
    }

    /*@RestController version:
    @PutMapping("/updateexistingstudent")
    public Student updateExistingStudent(@RequestBody StudentDto studentDto) throws Exception {
        return studentServiceImpl.updateStudent(studentDto);
    }
     */

    //@Controller version:
    @GetMapping("/student/update/{id}")
    public String updateExistingStudent(@PathVariable int id, Model model) throws StudentWithSuchAnIdDoesNotExistException {
        log.info("@Controller updateExistingStudent() was called");

        StudentDto studentDto = new StudentDto();
        Student student = studentServiceImpl.findStudentById(id);
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setAge(student.getAge());
        studentDto.setEmail(student.getEmail());

        model.addAttribute("studentDto", studentDto);
        return "/student/updateexistingstudent";
    }

    @PostMapping("/student/updatexistingstudentform")
    public String updateExistingStudentForm(@ModelAttribute StudentDto studentDto) {
        log.info("@Controller updateExistingStudentForm() was called");
        studentServiceImpl.updateStudent(studentDto);
        int id = studentDto.getId();

        return "redirect:/student/update/" + id;
    }

    /*@RestController version:
    @PostMapping("/checkifstudentexistsbyid")
    public boolean checkIfStudentExistsById(@RequestBody StudentIdDto id) {
        log.info("checkIfStudentExistsById() was called");
        return studentServiceImpl.checkIfStudentExistsById(id.getId());
    }
     */

    //@Controller version (not displayed on separate page):
    public boolean checkIfStudentExistsById(int id) {
        if (studentServiceImpl.findStudentById(id) != null) {
            return true;
        }
        return false;
    }

    /*@RestController version:
    @PostMapping("/findstudentbyid")
    public Optional<Student> findStudentById(@RequestBody StudentIdDto id) {
        log.info("findStudentById() was called");
        return studentServiceImpl.findStudentById(id.getId());
    }
     */

    //@Controller version
    @GetMapping("/student/{id}")
    public String findStudentById(@PathVariable int id, Model model) throws StudentWithSuchAnIdDoesNotExistException {
        log.info("findStudentById() was called");
        StudentDto studentDto = new StudentDto();
        Student student = studentServiceImpl.findStudentById(id);

        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setAge(student.getAge());
        studentDto.setEmail(student.getEmail());

        model.addAttribute("studentDto", studentDto);
        StudentIdDto studentIdDto = new StudentIdDto();
        model.addAttribute("idOfStudentToBeDeleted", studentIdDto);
        return "/student/studentinfo";
    }

    /*@RestController version:
    @PostMapping("/findallstudents")
    public List<Student> findAllStudents() {
        log.info("@RestController findAllStudents() was called");
        return studentServiceImpl.findAllStudents();
    }
     */

    //@Controller version
    @GetMapping("/student/listallstudents")
    public String findAllStudents(Model model) {
        log.info("@Controller findAllStudents() was called");
        model.addAttribute("listOfAllStudents", studentServiceImpl.findAllStudents());
        return "/student/listallstudents";
    }

    /*@RestController version:
    @DeleteMapping("/deletestudent/{id}")
    public void deleteStudentById(@PathVariable int id) {
        log.info("deleteStudentById() was called");
        studentServiceImpl.deleteStudentById(id);
    }
     */

    @PostMapping("/student/deletestudent")
    public String deleteStudentById(@ModelAttribute StudentIdDto idOfStudentToBeDeleted, Model model) {
        log.info("@Controller deleteStudentById() was called");
        studentServiceImpl.deleteStudentById(idOfStudentToBeDeleted.getId());
        model.addAttribute("studentDeletedMessage", "Student with the id: " + idOfStudentToBeDeleted.getId() + " was deleted.");
        model.addAttribute("studentDeleted", true);
        model.addAttribute("idOfStudentToBeDeleted", new StudentIdDto());
        return "/student/studentinfo";
    }

        /*@RestController version
    @PostMapping("/countalltherowsinthestudenttable")
    public long countAllTheRowsInTheStudentTable() {
        log.info("@RestController countAllTheRowsInTheStudentTable() was called");
        return studentServiceImpl.countAllTheRowsInTheStudentTable();
    }
     */

    //@Controller version:
    @GetMapping("/student/countallthestudenttablerows")
    public String countAllTheRowsInTheStudentTable(Model model) {
        log.info("@Controller countAllTheRowsInTheStudentTable() was called");
        model.addAttribute("numberOfRowsInTheStudentTable", studentServiceImpl.countAllTheStudentTableRows());
        return "/student/countallthestudenttablerows";
    }

    /*@RestController version:
    @PostMapping("/checkclassofstudent")
    public Class checkClass() {
        log.info("@RestController checkClass() was called");
        return studentServiceImpl.checkClass();
    }
     */

    //@Controller version
    @GetMapping("/student/checkclassofstudenttable")
    public String checkClassOfStudentTable(Model model) {
        log.info("@Controller checkClassOfStudentTable() was called");
        model.addAttribute("classOfStudentTable", studentServiceImpl.checkClassOfStudentTable().toString());
        return "/student/checkclassofstudenttable";
    }
}
