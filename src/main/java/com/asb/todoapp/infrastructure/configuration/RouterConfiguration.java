package com.asb.todoapp.infrastructure.configuration;

import com.asb.todoapp.todo.adapter.handler.TodoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfiguration {

   private static final String API_V1 = "/api/v1";
   private static final String TODO = API_V1 + "/todos";
   private static final String ID = "/{id}";

   @Bean
   public RouterFunction<ServerResponse> todoRouter(TodoHandler handler) {
      return route(GET(TODO), handler::getAll)
          .andRoute(GET(TODO + ID), handler::get)
          .andRoute(DELETE(TODO + ID), handler::delete)
          .andRoute(POST(TODO), handler::create);
   }
}
