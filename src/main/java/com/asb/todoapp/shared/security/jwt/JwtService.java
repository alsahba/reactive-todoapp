package com.asb.todoapp.shared.security.jwt;

import com.asb.todoapp.shared.exception.CustomSecurityException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public record JwtService(JwtProperties props) {

   public String generateToken(String username) {
      return Jwts.builder()
          .setSubject(username)
          .setExpiration(new Date(System.currentTimeMillis() + props.expiration()))
          .signWith(SignatureAlgorithm.HS512, props.secret().getBytes(StandardCharsets.UTF_8))
          .compact();
   }

   public Mono<String> getUsernameFromToken(String token) {
      try {
         return Mono.just(
             Jwts.parser()
                 .setSigningKey(props.secret().getBytes(StandardCharsets.UTF_8))
                 .parseClaimsJws(token)
                 .getBody()
                 .getSubject()
         );
      } catch (Exception e) {
         return Mono.error(new CustomSecurityException("Invalid JWT token"));
      }
   }

   public Mono<String> getTokenFromAuthHeader(ServerWebExchange exchange) {
      String authorizationHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
      if (authorizationHeader != null && authorizationHeader.startsWith(props.prefix())) {
         return Mono.just(authorizationHeader.substring(props.prefix().length()));
      }
      return Mono.error(new CustomSecurityException("Missing or invalid Authorization header"));
   }
}
