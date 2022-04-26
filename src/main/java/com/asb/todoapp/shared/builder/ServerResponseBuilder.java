package com.asb.todoapp.shared.builder;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

public class ServerResponseBuilder {

   private ServerResponseBuilder() {
   }

   public static Mono<ServerResponse> notFound() {
      return ServerResponse.notFound().build();
   }

   public static Mono<ServerResponse> badRequest() {
      return ServerResponse.badRequest().build();
   }

   public static <T> Mono<ServerResponse> ok(T data) {
      return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromValue(data));
   }

   public static Mono<ServerResponse> ok() {
      return ServerResponse.ok().build();
   }

   public static <T> Mono<ServerResponse> created(URI uri, T data) {
      return ServerResponse.created(uri).contentType(MediaType.APPLICATION_JSON).body(fromValue(data));
   }
}
