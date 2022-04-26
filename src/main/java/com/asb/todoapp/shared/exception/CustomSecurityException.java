package com.asb.todoapp.shared.exception;

import lombok.Getter;

@Getter
public class CustomSecurityException extends RuntimeException {

   public CustomSecurityException(String message) {
      super(message);
   }

}
