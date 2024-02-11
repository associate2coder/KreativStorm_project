package com.springbootproject.dto.student;

import com.springbootproject.dto.student.StudentDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentDtoListDto {
    private final List<StudentDto> studentDtoListDto = new ArrayList<>();
//
//    private StudentDto createDefaultStudentDto() {
//        StudentDto defaultStudentDto = new StudentDto();
//        defaultStudentDto.setId(1);
//        defaultStudentDto.setAge(1);
//        defaultStudentDto.setName("test");
//        defaultStudentDto.setEmail("test@test.com");
//        return defaultStudentDto;
//    }

    public void addStudentDto(StudentDto studentDto) {
//        if (studentDto == null) {
//            studentDto = createDefaultStudentDto();
//        }
        this.studentDtoListDto.add(studentDto);
    }
}
