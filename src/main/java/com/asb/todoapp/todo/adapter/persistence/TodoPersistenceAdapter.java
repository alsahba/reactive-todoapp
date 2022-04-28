package com.asb.todoapp.todo.adapter.persistence;

import com.asb.todoapp.shared.configuration.redis.RedisClient;
import com.asb.todoapp.shared.exception.BusinessRuleException;
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
   private final RedisClient redisClient;

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
   public Mono<Object> update(Todo todo) {
      return redisClient.get(todo.getId())
          .filter(Boolean.TRUE::equals)
          .flatMap(locked -> Mono.error(new BusinessRuleException("Todo is locked")))
          .switchIfEmpty(Mono.defer(() ->
                  redisClient.set(todo.getId(), true).then(
                      todoRepository.findById(todo.getId())
                          .flatMap(todoEntity -> {
                             todoEntity.update(todo);
                             return todoRepository.save(todoEntity).map(TodoEntity::toDomain);
                          }).switchIfEmpty(
                              Mono.defer(() -> Mono.error(new BusinessRuleException("Todo is not found")))
                          ).doOnSuccess(todo1 -> redisClient.set(todo.getId(), false).subscribe())
                  )
              )
          );
   }

   @Override
   public Mono<Todo> findById(String id) {
      return todoRepository.findById(id).map(TodoEntity::toDomain);
   }

   @Override
   public Mono<Object> delete(String id) {
      return redisClient.get(id)
          .filter(Boolean.TRUE::equals)
          .flatMap(locked -> Mono.error(new BusinessRuleException("Todo is locked")))
          .switchIfEmpty(todoRepository.deleteById(id));
   }
}
