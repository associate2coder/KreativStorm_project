package com.springbootproject.object;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="testobject")
public class TestObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testId;
    @Column
    @NotBlank(message= "must have a value")
    private String testString;
    @Column
    @Min(value = 1, message= "must have a value")
    private long testLong;
    @Column
    @NotBlank(message= "must have an email address")
    @Email
    private String email;

}