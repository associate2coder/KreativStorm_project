package com.newspringboottest.config;

import com.newspringboottest.objects.TestObject;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "testconfig")
@Data
public class TestConfig {

    @Bean
    public TestObject getTestObject() {
        return new TestObject();
    }
}
