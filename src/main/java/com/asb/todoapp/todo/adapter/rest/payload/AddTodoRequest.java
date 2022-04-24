package com.asb.todoapp.todo.adapter.rest.payload;

import com.asb.todoapp.todo.domain.enumeration.Importance;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTodoRequest {

   private String explanation;

   private Importance importance;

}
