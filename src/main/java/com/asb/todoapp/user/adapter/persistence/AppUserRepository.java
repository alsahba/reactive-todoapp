package com.asb.todoapp.user.adapter.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface AppUserRepository extends ReactiveMongoRepository<AppUser, String> {

   Mono<AppUser> findByUsername(String username);

}
