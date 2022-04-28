package com.asb.todoapp.user.application;

import com.asb.todoapp.shared.exception.CustomSecurityException;
import com.asb.todoapp.shared.security.AppPasswordEncoder;
import com.asb.todoapp.shared.security.jwt.JwtService;
import com.asb.todoapp.user.adapter.handler.payload.LoginRequest;
import com.asb.todoapp.user.adapter.handler.payload.LoginResponse;
import com.asb.todoapp.user.adapter.handler.payload.RegisterRequest;
import com.asb.todoapp.user.adapter.persistence.AppUser;
import com.asb.todoapp.user.adapter.persistence.AppUserRepository;
import com.asb.todoapp.user.domain.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements ReactiveUserDetailsService {

   private final AppUserRepository appUserRepository;
   private final AppPasswordEncoder passwordEncoder;
   private final JwtService jwtService;

   @Override
   public Mono<UserDetails> findByUsername(String username) {
      return appUserRepository.findByUsername(username)
          .flatMap(user -> Mono.justOrEmpty(new AppUserDetails(user)));
   }

   public Mono<Object> register(Mono<RegisterRequest> requestMono) {
      return requestMono.flatMap(
          request -> {
             var user = new AppUser(request.getUsername(), passwordEncoder.encode(request.getPassword()));
             return appUserRepository.findByUsername(user.getUsername())
                 .flatMap(registeredUser -> Mono.error(new CustomSecurityException("Username already exists")))
                 .switchIfEmpty(Mono.defer(() -> appUserRepository.save(user)));
          }
      );
   }

   public Mono<Object> login(Mono<LoginRequest> requestMono) {
      return requestMono.flatMap(request ->
         appUserRepository.findByUsername(request.getUsername())
             .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
             .map(user -> new LoginResponse(jwtService.generateToken(user.getUsername())))
             .switchIfEmpty(
                 Mono.defer(() -> Mono.error(new CustomSecurityException("Username or password is incorrect")))
             )
      );
   }
}
