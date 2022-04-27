package com.asb.todoapp.shared.configuration.mongo;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoProperties {

   private final String host;
   private final int port;
   private final String username;
   private final String password;

   @ConstructorBinding
   public MongoProperties(String host, int port, String username, String password) {
      this.host = host;
      this.port = port;
      this.username = username;
      this.password = password;
   }
}
