package com.study.springbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SpringbatchApplication {

	public static void main(String[] args) {
		log.info("Sample Spring Batch");
		SpringApplication.run(SpringbatchApplication.class, args);
	}

}
