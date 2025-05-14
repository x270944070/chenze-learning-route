package com.chenze.spring.security.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@org.springframework.boot.autoconfigure.SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }


}
