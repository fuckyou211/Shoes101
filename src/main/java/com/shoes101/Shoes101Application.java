package com.shoes101;

import tk.mybatis.spring.annotation.MapperScan;
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shoes101.mapper")
public class Shoes101Application {

    public static void main(String[] args) {

        SpringApplication.run(Shoes101Application.class, args);
        System.out.println("hi");
    }
}
