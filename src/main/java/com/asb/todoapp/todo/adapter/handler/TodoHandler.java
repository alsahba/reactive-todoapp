package com.asb.todoapp.todo.adapter.handler;

import com.asb.todoapp.todo.adapter.handler.payload.AddTodoRequest;
import com.asb.todoapp.todo.application.TodoService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public record TodoHandler(TodoService todoService) {

   public Mono<ServerResponse> getAll(ServerRequest request) {
      return todoService.getAll().collectList().flatMap(todo -> {
             if (todo.isEmpty()) {
                return ServerResponse.notFound().build();
             }
             return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromValue(todo));
          }
      );
   }

   public Mono<ServerResponse> get(ServerRequest request) {
      var id = request.pathVariable("id");
      return todoService.get(id)
          .flatMap(todo -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromValue(todo)))
          .switchIfEmpty(ServerResponse.notFound().build());
   }

   public Mono<ServerResponse> create(ServerRequest request) {
      return request.bodyToMono(AddTodoRequest.class)
          .flatMap(r -> todoService.create(r.toCommand())
              .flatMap(todo -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromValue(todo))
                  .switchIfEmpty(ServerResponse.badRequest().build())
              )
          );
   }

   public Mono<ServerResponse> delete(ServerRequest request) {
      var id = request.pathVariable("id");
      return todoService.delete(id)
          .then(ServerResponse.ok().build())
          .switchIfEmpty(ServerResponse.notFound().build());
   }

}
