package com.asb.todoapp.shared.configuration.redis;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {

   private final String host;
   private final int port;

   @ConstructorBinding
   public RedisProperties(String host, int port) {
      this.host = host;
      this.port = port;
   }
}
