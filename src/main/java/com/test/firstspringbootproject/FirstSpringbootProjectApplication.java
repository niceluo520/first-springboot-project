package com.test.firstspringbootproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

//@MapperScan     开启mybatis注解
@ServletComponentScan
@MapperScan("com.test.firstspringbootproject.**.dao")
@SpringBootApplication
// 导入spring配置文件
@ImportResource({"classpath:spring/applicationContext.xml"})
public class FirstSpringbootProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstSpringbootProjectApplication.class, args);
    }

}
