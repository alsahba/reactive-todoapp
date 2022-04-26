package com.asb.todoapp.shared.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public record JwtService(JwtConfigurationProps props) {

   public String createToken(String username) {
      return Jwts.builder()
          .setSubject(username)
          .setExpiration(new Date(System.currentTimeMillis() + props.getExpiration()))
          .signWith(SignatureAlgorithm.HS512, props.getSecret().getBytes(StandardCharsets.UTF_8))
          .compact();
   }

   public String getUsernameFromToken(String token) {
      Jws<Claims> claimsJws = Jwts.parser()
          .setSigningKey(props.getSecret().getBytes(StandardCharsets.UTF_8))
          .parseClaimsJws(token);
      return claimsJws.getBody().getSubject();
   }

   public String getTokenFromAuthHeader(ServerWebExchange exchange) {
      String authorizationHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
      if (authorizationHeader != null && authorizationHeader.startsWith(props.getPrefix())) {
         return authorizationHeader.substring(props.getPrefix().length());
      }
      return null;
   }
}
