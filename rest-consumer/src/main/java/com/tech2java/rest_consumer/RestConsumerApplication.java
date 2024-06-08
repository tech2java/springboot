package com.tech2java.rest_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.tech2java.rest_consumer.proxy")
public class RestConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestConsumerApplication.class, args);
	}

}
