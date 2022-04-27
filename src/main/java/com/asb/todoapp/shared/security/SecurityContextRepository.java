package com.asb.todoapp.shared.security;

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
      throw new RuntimeException("Not supported");
   }

   @Override
   public Mono<SecurityContext> load(ServerWebExchange swe) {
      var token = jwtService.getTokenFromAuthHeader(swe);
      var auth = new UsernamePasswordAuthenticationToken(token, token);
      return this.authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
   }
}
