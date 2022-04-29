# reactive-todoapp

It is an example of a reactive-todo application.

Tech-stack:
- Spring boot with WebFlux 
- Java 17
- MongoDB
- Redis
- Docker

You can try the project by running in the main directory:

```docker-compose up```

For detached mode you can add -d flag:

```docker-compose up -d```

Mongodb uses reactive streams to handle the database
and redis uses reactive streams to handle the lock-cache of todos.

You can check [non-reactive](https://github.com/alsahba/todoapp) form of the application. 


