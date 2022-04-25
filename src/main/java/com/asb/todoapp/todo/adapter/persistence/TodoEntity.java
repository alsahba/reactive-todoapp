package com.asb.todoapp.todo.adapter.persistence;

import com.asb.todoapp.todo.domain.Todo;
import com.asb.todoapp.todo.domain.enumeration.Importance;
import com.asb.todoapp.todo.domain.enumeration.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "todo")
@Getter
@Setter
@NoArgsConstructor
class TodoEntity {

   @Id
   private String id;

   private String explanation;

   private Importance importance;

   @CreatedDate
   private LocalDateTime creationDate;

   @LastModifiedDate
   private LocalDateTime lastModifiedDate;

   private Status status;

   public TodoEntity(Todo todo) {
      this.explanation = todo.getExplanation();
      this.importance = todo.getImportance();
      this.status = todo.getStatus();
   }

   public Todo toDomain() {
      return Todo.builder()
          .id(id)
          .explanation(explanation)
          .importance(importance)
          .status(status)
          .build();
   }
}
