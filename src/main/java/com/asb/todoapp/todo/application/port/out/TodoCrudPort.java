package com.asb.todoapp.todo.application.port.out;

import com.asb.todoapp.todo.domain.Todo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoCrudPort {

   Flux<Todo> findAll();

   Mono<Todo> save(Todo todo);

   Mono<Todo> findById(String id);

   Mono<Void> delete(String id);

}
