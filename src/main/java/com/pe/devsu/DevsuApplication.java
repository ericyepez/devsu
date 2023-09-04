package com.pe.devsu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.pe.devsu.controller",
        "com.pe.devsu.mapper",
        "com.pe.devsu.service",
        "com.pe.devsu.repository"})
public class DevsuApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevsuApplication.class, args);
    }

}
