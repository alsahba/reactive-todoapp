package com.asb.todoapp.shared.configuration.mongo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "spring.data.mongodb")
@ConstructorBinding
public record MongoProperties(String host, int port, String username, String password) {

}
