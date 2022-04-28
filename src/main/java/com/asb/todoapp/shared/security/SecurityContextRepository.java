package com.asb.todoapp.shared.security;

import com.asb.todoapp.shared.exception.CustomSecurityException;
import com.asb.todoapp.shared.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SecurityContextRepository implements ServerSecurityContextRepository {

   private final AuthenticationManager authenticationManager;
   private final JwtService jwtService;

   @Override
   public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
      throw new CustomSecurityException("Not supported!");
   }

   @Override
   public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
      return jwtService.getTokenFromAuthHeader(serverWebExchange)
          .flatMap(token -> authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(token, null)))
          .map(SecurityContextImpl::new);
   }
}
