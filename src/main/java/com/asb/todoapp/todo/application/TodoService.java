package com.asb.todoapp.todo.application;

import com.asb.todoapp.todo.application.port.in.command.AddTodoCommand;
import com.asb.todoapp.todo.application.port.out.TodoCrudPort;
import com.asb.todoapp.todo.domain.Todo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public record TodoService(TodoCrudPort todoCrudPort) {

   public Mono<Todo> create(AddTodoCommand command) {
      return todoCrudPort.save(command.toDomain());
   }

   public Mono<Void> delete(String id) {
      return todoCrudPort.delete(id);
   }

   public Mono<Todo> get(String id) {
      return todoCrudPort.findById(id);
   }

   public Flux<Todo> getAll() {
      return todoCrudPort.findAll();
   }

}
