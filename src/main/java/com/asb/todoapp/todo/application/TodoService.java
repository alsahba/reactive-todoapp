package com.asb.todoapp.todo.application;

import com.asb.todoapp.todo.application.port.in.AddTodoCommand;
import com.asb.todoapp.todo.application.port.in.TodoCrudUC;
import com.asb.todoapp.todo.application.port.in.UpdateTodoCommand;
import com.asb.todoapp.todo.application.port.out.TodoCrudPort;
import com.asb.todoapp.todo.domain.Todo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
record TodoService(TodoCrudPort todoCrudPort) implements TodoCrudUC {

   public Mono<Todo> create(AddTodoCommand command) {
      return todoCrudPort.save(command.toDomain());
   }

   public Mono<Todo> update(UpdateTodoCommand command) {
      return todoCrudPort.update(command.toDomain());
   }

   public Mono<Object> delete(String id) {
      return todoCrudPort.delete(id);
   }

   public Mono<Todo> get(String id) {
      return todoCrudPort.findById(id);
   }

   public Flux<Todo> getAll() {
      return todoCrudPort.findAll();
   }

}
