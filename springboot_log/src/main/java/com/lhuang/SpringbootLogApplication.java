package com.lhuang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.lhuang.mapper")
public class SpringbootLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootLogApplication.class, args);
	}
}
