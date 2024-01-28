package com.springbootproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestObjectDto {
    private int testId;
    private String testString;
    private long testLong;
    private String email;
}
