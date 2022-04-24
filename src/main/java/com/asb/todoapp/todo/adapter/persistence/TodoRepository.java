package com.asb.todoapp.todo.adapter.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TodoRepository extends ReactiveMongoRepository<TodoEntity, String> {
}
