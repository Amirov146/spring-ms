FROM openjdk:17-jdk-alpine AS build
WORKDIR /app

#COPY .mvn .mvn
#COPY mvnw .
#COPY pom.xml .
#COPY src src

COPY target/spring-boot-app.jar /app/spring-boot-app.jar

ARG MAVEN_PROFILE=default

RUN ./mvnw clean install -P${MAVEN_PROFILE} -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app

#COPY --from=build /app/target/spring-boot-app.jar /app/spring-boot-app.jar

EXPOSE 8080

ENV profile=default

CMD ["sh", "-c", "java -jar spring-boot-app.jar --spring.profiles.active=${profile}"]
