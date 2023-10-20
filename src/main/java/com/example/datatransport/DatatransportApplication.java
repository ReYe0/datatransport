package com.example.datatransport;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author xuy
 */
@SpringBootApplication
@EnableScheduling
//@MapperScan("com.example.datatransport.dao")
public class DatatransportApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatatransportApplication.class, args);
    }

}
