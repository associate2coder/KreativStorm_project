package com.springbootproject.service;

import com.springbootproject.object.Course;

import java.util.List;

public interface AdminService {
    void save(Course course);

    Course show(int id);

    List<Course> findAllCourses();

    void delete(int id);
}
