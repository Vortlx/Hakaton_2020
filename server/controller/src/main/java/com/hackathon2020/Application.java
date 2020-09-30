package com.hackathon2020;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {
        "com.hackathon2020.auth",
        "com.hackathon2020.client",
        "com.hackathon2020.meeting",
        "com.hackathon2020.employee",
        "com.hackathon2020.config",
        "com.hackathon2020.socket",
        "com.hackathon2020.security",
        "com.hackathon2020.security.jwt",
        "com.hackathon2020.converters",
        "com.hackathon2020.dao"
})
@EntityScan("com.hackathon2020.entities")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
