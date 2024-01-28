package com.springbootproject.config;

import com.springbootproject.object.TestObject;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "testconfig")
@Data
public class TestConfig {

    String testString;

    @Bean
    public TestObject getTestObject() {
        return new TestObject();
    }
}
