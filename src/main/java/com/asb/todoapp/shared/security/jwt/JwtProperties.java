package com.asb.todoapp.shared.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "jwt")
@ConstructorBinding
public record JwtProperties(String secret, String prefix, long expiration) {

}
