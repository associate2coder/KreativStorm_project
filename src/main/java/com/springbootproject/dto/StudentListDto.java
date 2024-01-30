package com.springbootproject.dto;

import com.springbootproject.object.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentListDto {
    private List<Student> testObjectList;

    public void addStudentToList(Student student) {
        testObjectList.add(student);
    }
}
