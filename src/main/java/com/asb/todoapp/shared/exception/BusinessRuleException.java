package com.asb.todoapp.shared.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class BusinessRuleException extends RuntimeException {

   public BusinessRuleException(String message) {
      super(message);
      log.error(message);
   }
}
