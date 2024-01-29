package com.springbootproject.dto;

import com.springbootproject.object.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto extends Student {
    private int testId;
    private String testString;
    private long testLong;
    private String email;
}
