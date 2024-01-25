package com.springbootproject.service;

import com.springbootproject.objects.TestObject;
import com.springbootproject.repository.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TestService<S> {

    @Autowired
    TestRepository testRepository;

    public long countAllTheRows() {
        log.info("countAllTheRows() was called");
        return testRepository.count();
    }

    public TestObject save(TestObject testObject) throws Exception {
        log.info("save() was called");
            return testRepository.save(testObject);
    }

    public List saveMultipleAtOnce(List list) {
        log.info("saveAll() was called");
        return testRepository.saveAll(list);
    }

    public boolean checkifItExistsById(int id) {
        log.info("checkifItExistsById() was called");
        return testRepository.existsById(id);
    }

    public Optional<TestObject> findById(int id) {
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
        return String.valueOf(testRepository.getClass());
    }
    }


