package com.asb.todoapp.todo.adapter.handler;

import com.asb.todoapp.shared.builder.ServerResponseBuilder;
import com.asb.todoapp.todo.adapter.handler.payload.AddTodoRequest;
import com.asb.todoapp.todo.application.port.in.TodoCrudUC;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public record TodoHandler(TodoCrudUC crudUC) {

   public Mono<ServerResponse> getAll(ServerRequest request) {
      return crudUC.getAll().collectList().flatMap(todo -> {
             if (todo.isEmpty()) {
                return ServerResponseBuilder.notFound();
             }
             return ServerResponseBuilder.ok(todo);
          }
      );
   }

   public Mono<ServerResponse> get(ServerRequest request) {
      var id = request.pathVariable("id");
      return crudUC.get(id)
          .flatMap(ServerResponseBuilder::ok)
          .switchIfEmpty(ServerResponseBuilder.notFound());
   }

   public Mono<ServerResponse> create(ServerRequest request) {
      return request.bodyToMono(AddTodoRequest.class)
          .flatMap(r -> crudUC.create(r.toCommand())
              .flatMap(ServerResponseBuilder::ok)
              .switchIfEmpty(ServerResponse.badRequest().build())
          );
   }

   public Mono<ServerResponse> delete(ServerRequest request) {
      var id = request.pathVariable("id");
      return crudUC.delete(id)
          .then(ServerResponseBuilder.ok())
          .switchIfEmpty(ServerResponseBuilder.notFound());
   }

}
