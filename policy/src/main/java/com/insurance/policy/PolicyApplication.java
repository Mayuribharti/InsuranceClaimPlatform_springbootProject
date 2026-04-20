package com.insurance.policy;

import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;


@OpenAPIDefinition(
	info = @Info(
		title = "Policy Service API",
		version = "1.0",
		description = "API documentation for the Policy Service"
	))
@SpringBootApplication
public class PolicyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolicyApplication.class, args);
	}

}
