package com.springbootproject.controller;

import com.springbootproject.dao.IdDao;
import com.springbootproject.objects.TestObject;
import com.springbootproject.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public TestObject addNewItem(@RequestBody TestObject testObject) throws Exception {
        log.info("@RestController addNewItem() was called");
        if (testObject != null) {
            return testService.save(testObject);
        } else {
            throw  new Exception("it's null in TestController/addNewItem()");
        }
    }
*/

    //Controller version
        @PostMapping("/addnewitem")
        public TestObject addNewItem(@RequestBody TestObject testObject) throws Exception {
            log.info("@Controller addNewItem() was called");
            if (testObject != null) {
                return testService.save(testObject);
            } else {
                throw  new Exception("it's null in TestController/addNewItem()");
            }
        }

    @PostMapping("/addmultiplenewitemsatonce")
    public List addMultipleNewItemsAtOnce(@RequestBody List<TestObject> testObjects) throws Exception {
        log.info("addMultipleNewItemsAtOnce() was called");
        for (int i = 0; i < testObjects.size(); i++) {
            if (testObjects.get(i) == null) {
                throw  new Exception("There is a null in TestController/addmultiplenewitemsatonce()");
            }
        }
        return testService.saveMultipleAtOnce(testObjects);
    }

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
        return testService.findAll();
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
        return testService.countAllTheRows();
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
        return testService.checkClass();
    }
      */

    //@Controller version
    @GetMapping("/checkclass")
    public String checkClass(Model model){
        log.info("@Controller checkClass() was called");
        model.addAttribute("className", testService.checkClass());
        return "checkclass";
    }

}
