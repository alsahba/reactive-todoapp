package com.asb.todoapp.shared.security;

import com.asb.todoapp.shared.security.jwt.JwtService;
import com.asb.todoapp.user.application.AppUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

   private final AppUserDetailsService userDetailsService;
   private final JwtService jwtService;

   @Override
   public Mono<Authentication> authenticate(Authentication authentication) {
      return Mono.justOrEmpty(authentication.getPrincipal())
          .flatMap(token -> jwtService.getUsernameFromToken(token.toString()))
          .flatMap(userDetailsService::findByUsername)
          .map(user -> new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()))
          .cast(Authentication.class)
          .switchIfEmpty(Mono.defer(Mono::empty))
          .onErrorResume(e -> Mono.empty());
   }
}
