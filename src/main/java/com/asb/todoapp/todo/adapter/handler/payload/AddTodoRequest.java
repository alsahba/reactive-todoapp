package com.asb.todoapp.todo.adapter.handler.payload;

import com.asb.todoapp.todo.application.port.in.command.AddTodoCommand;
import com.asb.todoapp.todo.domain.enumeration.Importance;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTodoRequest {

   private String explanation;

   private Importance importance;

   public AddTodoCommand toCommand() {
      return new AddTodoCommand(explanation, importance);
   }
}
