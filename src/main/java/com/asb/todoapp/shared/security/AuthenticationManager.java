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
      return Mono.justOrEmpty(authentication.getCredentials())
          .filter(token -> token instanceof String)
          .flatMap(token -> {
             var username = jwtService.getUsernameFromToken((String) token);
             return userDetailsService.findByUsername(username)
                 .map(userDetails ->
                     new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
                 );
          });
   }
}
