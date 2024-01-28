package com.springbootproject.repository;

import com.springbootproject.object.TestObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestObject, Integer> {
}
