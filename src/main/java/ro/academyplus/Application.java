package ro.academyplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    //TODO: remove
    public static int counter = 0;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}