package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EntityScan(basePackages="com.example.demo.model")
public class TrainersMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainersMicroserviceApplication.class, args);
	}

}
