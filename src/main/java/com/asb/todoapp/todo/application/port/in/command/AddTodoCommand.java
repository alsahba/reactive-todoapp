package com.asb.todoapp.todo.application.port.in.command;

import com.asb.todoapp.todo.domain.Todo;
import com.asb.todoapp.todo.domain.enumeration.Importance;
import com.asb.todoapp.todo.domain.enumeration.Status;

public record AddTodoCommand(String explanation,
                             Importance importance) {

   public Todo toDomain() {
      return Todo.builder()
          .status(Status.CREATED)
          .explanation(explanation)
          .importance(importance).build();
   }
}
