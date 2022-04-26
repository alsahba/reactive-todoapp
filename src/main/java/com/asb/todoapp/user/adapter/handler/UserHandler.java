package com.asb.todoapp.user.adapter.handler;

import com.asb.todoapp.shared.builder.ServerResponseBuilder;
import com.asb.todoapp.user.application.AppUserDetailsService;
import com.asb.todoapp.user.adapter.handler.payload.LoginRequest;
import com.asb.todoapp.user.adapter.handler.payload.RegisterRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public record UserHandler(AppUserDetailsService appUserDetailsService) {

   public Mono<ServerResponse> register(ServerRequest request) {
      var registerRequest = request.bodyToMono(RegisterRequest.class);
      return appUserDetailsService.register(registerRequest)
          .flatMap(user -> ServerResponseBuilder.ok())
          .switchIfEmpty(ServerResponseBuilder.badRequest());
   }

   public Mono<ServerResponse> login(ServerRequest request) {
      var loginRequest = request.bodyToMono(LoginRequest.class);
      return appUserDetailsService.login(loginRequest)
          .flatMap(ServerResponseBuilder::ok)
          .switchIfEmpty(ServerResponseBuilder.badRequest());
   }
}
