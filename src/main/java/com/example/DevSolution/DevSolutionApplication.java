package com.example.DevSolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.DevSolution")
public class DevSolutionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevSolutionApplication.class, args);
    }

}
