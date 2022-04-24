package com.asb.todoapp.todo.adapter.rest;

import com.asb.todoapp.todo.adapter.persistence.TodoEntity;
import com.asb.todoapp.todo.adapter.persistence.TodoRepository;
import com.asb.todoapp.todo.adapter.rest.payload.AddTodoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

   private final TodoRepository todoRepository;

   @GetMapping
   public Flux<TodoEntity> getTodos() {
      return todoRepository.findAll();
   }

   @GetMapping("/{id}")
   public Mono<TodoEntity> getTodo(String id) {
      return todoRepository.findById(id);
   }

   @PostMapping
   public Mono<TodoEntity> createTodo(@RequestBody AddTodoRequest request) {
      var todo = TodoEntity.builder()
          .importance(request.getImportance())
          .explanation(request.getExplanation())
          .build();

      return todoRepository.save(todo);
   }

}
