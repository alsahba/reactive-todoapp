package com.asb.todoapp.todo.domain;

import com.asb.todoapp.todo.domain.enumeration.Importance;
import com.asb.todoapp.todo.domain.enumeration.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Todo {

   private String id;
   private String explanation;
   private Importance importance;
   private Status status;

}
