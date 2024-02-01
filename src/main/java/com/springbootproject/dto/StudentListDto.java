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
    private List<StudentDto> studentListDto;

    public void addStudentToList(StudentDto studentDto) {
        studentListDto.add(studentDto);
    }
}
