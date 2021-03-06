package com.asb.todoapp.todo.adapter.handler;

import com.asb.todoapp.shared.builder.ServerResponseBuilder;
import com.asb.todoapp.todo.adapter.handler.payload.AddTodoRequest;
import com.asb.todoapp.todo.adapter.handler.payload.UpdateTodoRequest;
import com.asb.todoapp.todo.application.port.in.TodoCrudUC;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public record TodoHandler(TodoCrudUC todoCrud) {

   public Mono<ServerResponse> getAll(ServerRequest request) {
      return todoCrud.getAll().collectList().flatMap(ServerResponseBuilder::ok);
   }

   public Mono<ServerResponse> get(ServerRequest request) {
      var id = request.pathVariable("id");
      return todoCrud.get(id)
          .flatMap(ServerResponseBuilder::ok)
          .switchIfEmpty(Mono.defer(ServerResponseBuilder::notFound));
   }

   public Mono<ServerResponse> create(ServerRequest request) {
      return request.bodyToMono(AddTodoRequest.class)
          .flatMap(r ->
              todoCrud.create(r.toCommand()).flatMap(ServerResponseBuilder::ok)
          );
   }

   public Mono<ServerResponse> update(ServerRequest request) {
      var id = request.pathVariable("id");
      return request.bodyToMono(UpdateTodoRequest.class)
          .flatMap(r -> todoCrud.update(r.toCommand(id)))
          .flatMap(ServerResponseBuilder::ok)
          .onErrorResume(ServerResponseBuilder::badRequest);
   }

   public Mono<ServerResponse> delete(ServerRequest request) {
      var id = request.pathVariable("id");
      return todoCrud.delete(id)
          .flatMap(ServerResponseBuilder::ok)
          .onErrorResume(ServerResponseBuilder::badRequest);
   }

}
