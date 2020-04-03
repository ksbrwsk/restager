package de.ksbrwsk.restager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class RestagerApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(RestagerApplication.class, args);
        System.in.read();
    }
}
