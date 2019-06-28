package com.elitel.raindemo4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.elitel.raindemo4.dao")
public class RainDemo4Application {

    public static void main(String[] args) {
        SpringApplication.run(RainDemo4Application.class, args);
    }

}
