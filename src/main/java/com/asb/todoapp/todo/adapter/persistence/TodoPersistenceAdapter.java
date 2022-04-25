package com.asb.todoapp.todo.adapter.persistence;

import com.asb.todoapp.todo.application.port.out.TodoCrudPort;
import com.asb.todoapp.todo.domain.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
class TodoPersistenceAdapter implements TodoCrudPort {

   private final TodoRepository todoRepository;

   @Override
   public Flux<Todo> findAll() {
      return todoRepository.findAll().map(TodoEntity::toDomain);
   }

   @Override
   public Mono<Todo> save(Todo todo) {
      var entity = new TodoEntity(todo);
      return todoRepository.save(entity).map(TodoEntity::toDomain);
   }

   @Override
   public Mono<Todo> findById(String id) {
      return todoRepository.findById(id).map(TodoEntity::toDomain);
   }

   @Override
   public Mono<Void> delete(String id) {
      return todoRepository.deleteById(id);
   }
}
