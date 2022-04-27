package com.asb.todoapp.todo.adapter.persistence;

import com.asb.todoapp.shared.configuration.redis.RedisClient;
import com.asb.todoapp.todo.application.port.out.TodoCrudPort;
import com.asb.todoapp.todo.domain.Todo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
class TodoPersistenceAdapter implements TodoCrudPort {

   private final TodoRepository todoRepository;
   private final RedisClient redisClient;

   @Override
   public Flux<Todo> findAll() {
      return todoRepository.findAll().map(TodoEntity::toDomain);
   }

   @Override
   public Mono<Todo> save(Todo todo) {
      var entity = new TodoEntity(todo);
      return todoRepository.save(entity)
          .doOnSuccess(todoEntity -> redisClient.set(todoEntity.getId(), true))
          .map(TodoEntity::toDomain);
   }

   @Override
   public Mono<Todo> findById(String id) {
      return todoRepository.findById(id).map(TodoEntity::toDomain);
   }

   @Override
   public Mono<Object> delete(String id) {
      return redisClient.get(id)
          .flatMap(locked -> Mono.error(new RuntimeException("Todo is locked")))
          .switchIfEmpty(todoRepository.deleteById(id));
   }
}
