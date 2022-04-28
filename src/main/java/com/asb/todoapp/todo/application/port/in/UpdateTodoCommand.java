package com.asb.todoapp.todo.application.port.in;

import com.asb.todoapp.todo.domain.Todo;
import com.asb.todoapp.todo.domain.enumeration.Importance;
import com.asb.todoapp.todo.domain.enumeration.Status;

public record UpdateTodoCommand(String id, String explanation, Importance importance, Status status) {

   public Todo toDomain() {
      return Todo.builder()
          .id(id)
          .explanation(explanation)
          .importance(importance)
          .status(status)
          .build();
   }
}
