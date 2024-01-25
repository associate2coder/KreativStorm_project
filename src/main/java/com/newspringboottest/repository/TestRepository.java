package com.newspringboottest.repository;

import com.newspringboottest.objects.TestObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestObject, Integer> {
}
