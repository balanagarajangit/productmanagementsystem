package com.project.product_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages = {"com.project.product_management_system", "com.project.product_management_system.config"})
public class ProductManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductManagementSystemApplication.class, args);
    }
}

