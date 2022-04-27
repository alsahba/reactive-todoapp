package com.asb.todoapp.todo.application.port.in;

import com.asb.todoapp.todo.domain.Todo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoCrudUC {

   Mono<Todo> create(AddTodoCommand command);

   Mono<Object> delete(String id);

   Mono<Todo> get(String id);

   Flux<Todo> getAll();

}
