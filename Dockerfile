FROM openjdk:17-jdk-slim-buster as build

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
RUN ./mvnw dependency:go-offline

COPY src src
RUN ./mvnw package -DskipTests -q

FROM openjdk:17-jdk-slim-buster

COPY --from=build target/todoapp-0.0.1.jar todoapp-0.0.1.jar

ENTRYPOINT ["java", "-jar", "todoapp-0.0.1.jar"]