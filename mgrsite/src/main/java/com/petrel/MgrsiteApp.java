package com.petrel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.petrel.mapper")
@EnableTransactionManagement
@PropertySource({"db.properties"})
public class MgrsiteApp {

    public static void main(String[] args) {
        SpringApplication.run(MgrsiteApp.class,args);
    }
}
