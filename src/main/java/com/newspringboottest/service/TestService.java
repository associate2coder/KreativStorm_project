package com.newspringboottest.service;


import com.newspringboottest.objects.TestObject;
import com.newspringboottest.repository.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TestService {

    @Autowired
    TestRepository testRepository;

    public long countAll() {
        log.info("countAll() was called");
        return testRepository.count();
    }

    public TestObject save(TestObject testObject) throws Exception {
        log.info("save() was called");
            return testRepository.save(testObject);
    }

    public boolean checkifItExistsById (int id) {
        log.info("checkifItExistsById() was called");
        return testRepository.existsById(id);
    }

    public Optional<TestObject> findById (int id) {
        log.info("findById() was called");
        return testRepository.findById(id);
    }

    public List<TestObject> findAll() {
        log.info("finAll() was called");
        return testRepository.findAll();
    }

    public void deleteById(int id) {
        log.info("deleteById() was called");
        testRepository.deleteById(id);
    }

    public String checkClass() {
        log.info("checkClass() was called");
        String nameOfClass = String.valueOf(testRepository.getClass());
        return nameOfClass;
    }
    }


