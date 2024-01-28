package com.springbootproject.controller;

import com.springbootproject.dao.IdDao;
import com.springbootproject.object.TestObject;
import com.springbootproject.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class TestController {

    @Autowired
    TestService testService;

    /*@RestController version
    @GetMapping("/test")
    public String test() {
        log.info("@RestController test() was called");
        return "Hello World";
    }
*/

    //Controller version
    @GetMapping("/")
    public String test(Model model) {
        log.info("@Controller test() was called");
        model.addAttribute("helloworld", "Hello World");
        return "index";
    }

        /*@RestController version
    @PostMapping("/addnewitem")
    public TestObject addNewItem(@RequestBody TestObject testObject) throws NullPointerException {
        log.info("@RestController addNewItem() was called");
        if (testObject != null) {
            return studentService.save(testObject);
        } else {
            throw  new NullPointerException("it's null in TestController/addNewItem()");
        }
    }
*/

    //


    //Controller version:
    @GetMapping("/addnew")
    public ModelAndView addNewItemForm() {
        log.info("@Controller addNewItemForm() was called");
        ModelAndView mav = new ModelAndView("addnew");
        TestObject testObjectSomething = new TestObject();
        mav.addObject("testObject", testObjectSomething);
        return mav;
    }

    @PostMapping("/addNewItemTest")
    public String addNewItem(@ModelAttribute TestObject testObject) {
        log.info("@Controller addNewItem() was called");
        if (testObject != null) {
            testService.save(testObject);
            return "redirect:/";
        } else {
            throw new NullPointerException("It is null in TestController/addNewItem()");
        }
    }

    /*RestController version:
    @PostMapping("/addmultiplenewitemsatonce")
    public List addMultipleNewItemsAtOnce(@RequestBody List<TestObject> testObjects) throws Exception {
        log.info("addMultipleNewItemsAtOnce() was called");
        for (int i = 0; i < testObjects.size(); i++) {
            if (testObjects.get(i) == null) {
                throw new Exception("There is a null in TestController/addmultiplenewitemsatonce()");
            }
        }
        return studentService.saveMultipleAtOnce(testObjects);
    }

     */


    //Controller version
    @PostMapping("/addmultiplenewitemsatonce")
    public List addMultipleNewItemsAtOnce(@RequestBody List<TestObject> testObjects) throws Exception {
        log.info("addMultipleNewItemsAtOnce() was called");
        for (int i = 0; i < testObjects.size(); i++) {
            if (testObjects.get(i) == null) {
                throw new Exception("There is a null in TestController/addmultiplenewitemsatonce()");
            }
        }
        return testService.saveMultipleAtOnce(testObjects);
    }


    //Controller version:
//    @GetMapping("/createList")
//    public String showCreateForm(Model model) {
//        TestObjectListDto testObjectListDto = new TestObjectListDto();
//
//        for (int i = 1; i <= 3; i++) {
//            testObjectListDto.addTestObjectToList(new TestObject());
//        }
//
//        model.addAttribute("testObjectDto", testObjectListDto);
//        return "addnewlist";
//    }
//
//    @GetMapping ("/addnewlist")
//    public ModelAndView addNewListForm(){
//        log.info("@Controller addNewListForm() was called");
//        ModelAndView mav = new ModelAndView("addnewlist");
//        TestObject testObjectSomething = new TestObject();
//        mav.addObject("testObject", testObjectSomething);
//        return mav;
//    }
//
//    @PostMapping("/addNewListTest")
//    public String addNewList(@ModelAttribute TestObject testObject) {
//        log.info("@Controller addNewList() was called");
//        if (testObject != null) {
//            studentService.save(testObject);
//            return "redirect:/";
//        } else {
//            throw new NullPointerException("It is null in TestController/addNewList()");
//        }
//    }

    //RestController
    @PostMapping("/checkifitexistsbyid")
    public boolean checkIfItExistsById(@RequestBody IdDao id) {
        log.info("checkIfItExistsById() was called");
        return testService.checkIfItExistsById(id.getId());
    }

    @PostMapping("/findbyid")
    public Optional<TestObject> findById(@RequestBody IdDao id) {
        log.info("findById() was called");
        return testService.findById(id.getId());
    }


     /*@RestController version
    @PostMapping("/findall")
    public List<TestObject> findAll() {
        log.info("@RestController findAll() was called");
        return studentService.findAll();
    }
    */

    //@Controller version
    @GetMapping("/findall")
    public String findAll(Model model) {
        log.info("@Controller findAll() was called");
        model.addAttribute("testObjectList", testService.findAll());
        return "findall";
    }

  /*@RestController version
    @PostMapping("/countalltherows")
    public long countAllTheRows() {
        log.info("@RestController countAllTheRows() was called");
        return studentService.countAllTheRows();
    }
   */

    //@Controller version:
    @GetMapping("/countalltherows")
    public String countAllTheRows(Model model) {
        log.info("@Controller countAllTheRows() was called");
        model.addAttribute("numberOfRows", testService.countAllTheRows());
        return "countalltherows";
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        log.info("deleteById() was called");
        testService.deleteById(id);
    }

     /*@RestController version
    @PostMapping("/checkclass")
    public String checkClass() {
        log.info("@RestController checkClass() was called");
        return studentService.checkClass();
    }
      */

    //@Controller version
    @GetMapping("/checkclass")
    public String checkClass(Model model) {
        log.info("@Controller checkClass() was called");
        model.addAttribute("className", testService.checkClass());
        return "checkclass";
    }

}
