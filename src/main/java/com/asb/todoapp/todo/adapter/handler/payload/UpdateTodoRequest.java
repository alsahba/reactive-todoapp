package com.asb.todoapp.todo.adapter.handler.payload;

import com.asb.todoapp.todo.application.port.in.UpdateTodoCommand;
import com.asb.todoapp.todo.domain.enumeration.Importance;
import com.asb.todoapp.todo.domain.enumeration.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTodoRequest {

   private String explanation;

   private Importance importance;

   private Status status;

   public UpdateTodoCommand toCommand(String id) {
      return new UpdateTodoCommand(id, explanation, importance, status);
   }

}
