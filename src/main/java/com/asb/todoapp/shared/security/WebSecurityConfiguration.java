package com.asb.todoapp.shared.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {

   private final SecurityContextRepository securityContextRepository;
   private final AuthenticationManager authenticationManager;

   @Bean
   public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
      return http.csrf().disable()
          .authorizeExchange()
          .pathMatchers("/api/v1/users/**").permitAll()
          .anyExchange().authenticated()
          .and().httpBasic().disable()
          .formLogin().disable()
          .authenticationManager(authenticationManager)
          .securityContextRepository(securityContextRepository)
          .build();
   }
}
