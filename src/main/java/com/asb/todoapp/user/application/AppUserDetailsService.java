package com.asb.todoapp.user.application;

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
          .flatMap(a -> Mono.justOrEmpty(new AppUserDetails(a)));
   }

   public Mono<AppUser> register(Mono<RegisterRequest> request) {
      return request.flatMap(r -> {
         AppUser user = new AppUser(r.getUsername(), passwordEncoder.encode(r.getPassword()));
         return appUserRepository.findByUsername(r.getUsername())
             .switchIfEmpty(appUserRepository.save(user));
      });
   }

   public Mono<LoginResponse> login(Mono<LoginRequest> request) {
      return request.flatMap(r -> appUserRepository.findByUsername(r.getUsername())
          .flatMap(u -> {
             if (passwordEncoder.matches(r.getPassword(), u.getPassword())) {
                var token = jwtService.createToken(u.getUsername());
                return Mono.justOrEmpty(new LoginResponse(token));
             } else {
                return Mono.error(new RuntimeException("Invalid username or password"));
             }
          }));
   }


}
