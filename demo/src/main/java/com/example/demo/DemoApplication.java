package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication//(scanBasePackages = {"com.*"})
@EnableAutoConfiguration
@EnableReactiveMongoRepositories
@ComponentScan
@PropertySource("application.yml")
@OpenAPIDefinition(info = @Info(title = "Swagger Demo", version = "1.0", description = "Documentation APIs v1.0"))
public class DemoApplication {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

	}

}
