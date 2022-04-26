package com.asb.todoapp;

import com.asb.todoapp.shared.configuration.MongoConfigurationProps;
import com.asb.todoapp.shared.security.jwt.JwtConfigurationProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({MongoConfigurationProps.class, JwtConfigurationProps.class})
public class TodoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoappApplication.class, args);
	}

}
