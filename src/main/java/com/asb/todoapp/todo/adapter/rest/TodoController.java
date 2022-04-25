package com.asb.todoapp.todo.adapter.rest;

import com.asb.todoapp.todo.adapter.rest.payload.AddTodoRequest;
import com.asb.todoapp.todo.application.TodoService;
import com.asb.todoapp.todo.domain.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

   private final TodoService todoService;

   @GetMapping
   public Flux<Todo> getAll() {
      return todoService.getAll();
   }

   @GetMapping("/{id}")
   public Mono<Todo> get(String id) {
      return todoService.get(id);
   }

   @PostMapping
   public Mono<Todo> create(@RequestBody AddTodoRequest request) {
      var command = request.toCommand();
      return todoService.create(command);
   }

   @DeleteMapping
   public Mono<Void> delete(String id) {
      return todoService.delete(id);
   }

}
