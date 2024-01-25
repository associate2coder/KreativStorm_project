package com.springbootproject.controller;

import com.springbootproject.dao.IdDao;
import com.springbootproject.objects.TestObject;
import com.springbootproject.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/test")
    public String test() {
        log.info("test() was called");
        return "Hello World";
    }

    @PostMapping("/addnewitem")
    public TestObject addNewItem(@RequestBody TestObject testObject) throws Exception {
        log.info("addNewItem() was called");
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

    @PostMapping("/findall")
    public List<TestObject> findAll() {
        log.info("findAll() was called");
        return testService.findAll();
    }

    @PostMapping("/countalltherows")
    public long countAllTheRows() {
        log.info("countAllTheRows() was called");
        return testService.countAllTheRows();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        log.info("deleteById() was called");
        testService.deleteById(id);
    }

    @PostMapping("/checkclass")
    public String checkClass() {
        log.info("checkClass() was called");
        return testService.checkClass();
    }
}
