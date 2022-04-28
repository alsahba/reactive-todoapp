package com.asb.todoapp.shared.configuration.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "redis")
@ConstructorBinding
public record RedisProperties(String host, int port) {

}
