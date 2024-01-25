package com.newspringboottest.controller;


import com.newspringboottest.objects.TestObject;
import com.newspringboottest.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/addTestObject")
    public void addNewItem(@RequestBody TestObject testObject) throws Exception {
        log.info("addNewItem() was called");
        if (testObject != null) {
            testService.save(testObject);
        } else {
            throw  new Exception("it's null in Test Controller/addTestObject()");
        }
    }

    @PostMapping("/getall")
    public List<TestObject> returnAll() {
        log.info("returnAll() was called");
        return testService.findAll();
    }

}
