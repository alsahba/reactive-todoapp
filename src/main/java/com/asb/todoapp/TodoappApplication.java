package com.asb.todoapp;

import com.asb.todoapp.shared.configuration.mongo.MongoProperties;
import com.asb.todoapp.shared.configuration.redis.RedisProperties;
import com.asb.todoapp.shared.security.jwt.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({MongoProperties.class, JwtProperties.class, RedisProperties.class})
public class TodoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoappApplication.class, args);
	}

}
