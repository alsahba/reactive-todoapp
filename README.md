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

Rx-Spring security and JWT used for protection of the application. 
User registration/login operations can be done from the /users path. Authentication is provided via username and password.
When the user logs in, a JWT is created and returned in the response. In Todo requests, this token information is expected in the authorization header.
If the token has not expired, the valid token contains username information and accordingly this information is added to the security context.

You can check [non-reactive](https://github.com/alsahba/todoapp) form of the application. 


