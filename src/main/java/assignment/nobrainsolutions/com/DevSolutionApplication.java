package assignment.nobrainsolutions.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "assignment.nobrainsolutions.com")
public class DevSolutionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevSolutionApplication.class, args);
    }

}
