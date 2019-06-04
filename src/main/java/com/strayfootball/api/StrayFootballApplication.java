package com.strayfootball.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = "com.strayfootball.api.dao")
public class StrayFootballApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrayFootballApplication.class, args);
    }

}
