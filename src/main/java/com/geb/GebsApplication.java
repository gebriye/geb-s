package com.geb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan({ "com.geb" })
public class GebsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GebsApplication.class, args);
	}
}
