package com.accio.point_sale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Point Sale", version = "1.0.0", description = "APIs Swagger Point Sale"))
@SpringBootApplication
public class PointSaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointSaleApplication.class, args);
	}

}
