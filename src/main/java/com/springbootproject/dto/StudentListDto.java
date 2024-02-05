package com.springbootproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentListDto {
    private List<StudentDto> studentDtoList = new ArrayList<>();

//    private StudentDto studentDtoCreationOfDefaultValues(StudentDto studentDtoToGetDefaultValues) {
//        studentDtoToGetDefaultValues.setId(1);
//        studentDtoToGetDefaultValues.setAge(1);
//        studentDtoToGetDefaultValues.setName("test");
//        studentDtoToGetDefaultValues.setEmail("test@test.com");
//
//        return studentDtoToGetDefaultValues;
//    }

    public void addStudentDto(StudentDto studentDto) {
        this.studentDtoList.add(studentDto);

//        if (studentDtoToAdd == null) {
//            this.studentListDto.add(studentDtoCreationOfDefaultValues(studentDtoToAdd));
//        } else {
//            studentListDto.add(studentDtoToAdd);
//        }
    }
}
