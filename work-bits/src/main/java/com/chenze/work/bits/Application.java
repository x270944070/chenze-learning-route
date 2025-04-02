package com.chenze.work.bits;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/// ## 你好
/// 1. abc
/// 1. zxv
/// 3. fdk
/// 4. 你啊哈
@SpringBootApplication
@MapperScan({"com.chenze.work.bits.dao"})
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
