package com.chenze.work.bits;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.chenze.work.bits.dao"})
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
