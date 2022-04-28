package com.asb.todoapp.shared.configuration.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.*;

@Configuration
@RequiredArgsConstructor
public class RedisConfiguration {

   private final RedisProperties redisProperties;

   @Bean
   public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
      return new LettuceConnectionFactory(redisProperties.host(), redisProperties.port());
   }

   @Bean
   public ReactiveRedisOperations<String, Boolean> redisOperations(LettuceConnectionFactory connectionFactory) {
      var serializationContext = RedisSerializationContext
          .<String, Boolean>newSerializationContext(new StringRedisSerializer())
          .key(new StringRedisSerializer())
          .value(new GenericToStringSerializer<>(Boolean.class))
          .hashKey(new Jackson2JsonRedisSerializer<>(String.class))
          .hashValue(new GenericJackson2JsonRedisSerializer())
          .build();
      return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
   }
}
