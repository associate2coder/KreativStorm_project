package com.springbootproject.dto;

import com.springbootproject.object.TestObject;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TestObjectDto {
    private int testId;
    private String testString;
    private long testLong;
    private String email;

    public TestObjectDto(String testString, String email) {
        this.testString = testString;
        this.email = email;
    }
}
