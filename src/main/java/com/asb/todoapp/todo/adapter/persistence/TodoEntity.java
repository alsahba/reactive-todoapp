package com.asb.todoapp.todo.adapter.persistence;

import com.asb.todoapp.todo.domain.enumeration.Importance;
import com.asb.todoapp.todo.domain.enumeration.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@Setter
@Builder
public class TodoEntity {

   @Id
   private String id;

   private String explanation;

   private Importance importance;

   @CreatedDate
   private LocalDateTime creationDate;

   @LastModifiedDate
   private LocalDateTime lastModifiedDate;

   private Status status;
}
