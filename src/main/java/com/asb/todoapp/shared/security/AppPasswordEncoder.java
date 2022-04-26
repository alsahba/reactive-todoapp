package com.asb.todoapp.shared.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppPasswordEncoder implements PasswordEncoder {

   private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

   @Override
   public String encode(CharSequence rawPassword) {
      return passwordEncoder.encode(rawPassword);
   }

   @Override
   public boolean matches(CharSequence rawPassword, String encodedPassword) {
      return passwordEncoder.matches(rawPassword, encodedPassword);
   }
}
