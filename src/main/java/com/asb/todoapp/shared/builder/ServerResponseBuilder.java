package com.asb.todoapp.shared.builder;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

public abstract class ServerResponseBuilder {

   public static Mono<ServerResponse> notFound() {
      return ServerResponse.notFound().build();
   }

   public static Mono<ServerResponse> badRequest() {
      return ServerResponse.badRequest().build();
   }

   public static Mono<ServerResponse> badRequest(Throwable e) {
      return ServerResponse.badRequest().contentType(APPLICATION_JSON).body(fromValue(e.getMessage()));
   }

   public static <T> Mono<ServerResponse> ok(T data) {
      return ServerResponse.ok().contentType(APPLICATION_JSON).body(fromValue(data));
   }

   public static Mono<ServerResponse> ok() {
      return ServerResponse.ok().build();
   }

   public static <T> Mono<ServerResponse> created(URI uri, T data) {
      return ServerResponse.created(uri).contentType(APPLICATION_JSON).body(fromValue(data));
   }
}
