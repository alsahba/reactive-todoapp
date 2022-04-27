package com.asb.todoapp.shared.configuration.mongo;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.asb"})
@EnableReactiveMongoAuditing
@RequiredArgsConstructor
public class MongoConfiguration extends AbstractReactiveMongoConfiguration {

   private final MongoProperties mongoProperties;

   @Bean
   public ReactiveMongoTransactionManager transactionManager(ReactiveMongoDatabaseFactory dbFactory) {
      return new ReactiveMongoTransactionManager(dbFactory);
   }

   @Override
   @Bean
   public MongoClient reactiveMongoClient() {
      String uriBuilder = "mongodb://" + mongoProperties.getUsername() +
          ":" + mongoProperties.getPassword() +
          "@" + mongoProperties.getHost() +
          ":" + mongoProperties.getPort();
      return MongoClients.create(uriBuilder);
   }

   @Bean
   public ReactiveMongoTemplate reactiveMongoTemplate() {
      return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
   }

   @Override
   protected String getDatabaseName() {
      return "todoapp";
   }

}
