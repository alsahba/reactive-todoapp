package com.asb.todoapp.shared.configuration;

import com.asb.todoapp.user.adapter.handler.UserHandler;
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
   private static final String USER = API_V1 + "/users";
   private static final String ID = "/{id}";

   @Bean
   public RouterFunction<ServerResponse> todoRouter(TodoHandler handler) {
      return route(GET(TODO), handler::getAll)
          .andRoute(GET(TODO + ID), handler::get)
          .andRoute(PUT(TODO + ID), handler::update)
          .andRoute(DELETE(TODO + ID), handler::delete)
          .andRoute(POST(TODO), handler::create);
   }

   @Bean
   public RouterFunction<ServerResponse> userRouter(UserHandler handler) {
      return route(POST(USER), handler::register)
          .andRoute(POST(USER + "/login"), handler::login);
   }
}
