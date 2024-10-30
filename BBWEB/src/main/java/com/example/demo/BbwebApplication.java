package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("model")
@SpringBootApplication
public class BbwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbwebApplication.class, args);
	}

}
