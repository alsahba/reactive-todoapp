package com.asb.todoapp.shared.configuration.redis;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public record RedisClient(ReactiveRedisOperations<String, Boolean> redisOperations) {

   public Mono<Boolean> set(String key, boolean value) {
      return redisOperations.opsForValue().set(key, value);
   }

   public Mono<Boolean> get(String key) {
      return redisOperations.opsForValue().get(key);
   }
}
