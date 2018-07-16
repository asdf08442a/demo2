package com.enterprise.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.enterprise.demo")
public class DemoWebApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DemoWebApplication.class);
        // 屏蔽命令行访问属性
        springApplication.setAddCommandLineProperties(false);
        springApplication.run(args);
    }
}
