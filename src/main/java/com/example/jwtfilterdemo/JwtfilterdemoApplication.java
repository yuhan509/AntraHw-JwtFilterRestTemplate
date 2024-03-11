package com.example.jwtfilterdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class JwtfilterdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtfilterdemoApplication.class, args);
	}

}
