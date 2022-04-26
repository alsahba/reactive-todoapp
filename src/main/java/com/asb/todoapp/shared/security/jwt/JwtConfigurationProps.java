package com.asb.todoapp.shared.security.jwt;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigurationProps {

   private final String secret;
   private final String prefix;
   private final long expiration;

   @ConstructorBinding
   public JwtConfigurationProps(String secret, long expiration, String prefix) {
      this.secret = secret;
      this.expiration = expiration;
      this.prefix = prefix;
   }
}
