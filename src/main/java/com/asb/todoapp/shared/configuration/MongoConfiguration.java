package com.asb.todoapp.shared.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.asb"})
@EnableReactiveMongoAuditing
public class MongoConfiguration extends AbstractReactiveMongoConfiguration {

   @Override
   protected String getDatabaseName() {
      return "todoapp";
   }

}
