package com.demo.top;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TopApplication {

    public static void main(String[] args) {
        SpringApplication.run(TopApplication.class, args);
    }

}
