package com.springbootproject.service;

import com.springbootproject.object.Student;

public interface EnrolmentService {

    /**
     * Method to enroll one student with into one course.
     * @param studentId - student ID number as it is saved in the database
     * @param courseId - course ID number as it is saved in the database
     * @return Student object which has been saved with the database
     */
    Student enroll(int studentId, int courseId);

    /**
     * Method to unenroll the student from the specific course ().
     * @param studentId - student ID number as it is saved in the database
     * @param courseId - course ID number as it is saved in the database
     * @return Student object which has been saved with the database
     */
    Student unenroll(int studentId, int courseId);


}
