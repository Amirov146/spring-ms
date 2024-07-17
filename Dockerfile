FROM openjdk:17-oracle AS dependencies
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

#RUN ./mvnw dependency:go-offline -B
#RUN ./mvnw clean install -DskipTests

FROM openjdk:17-oracle AS build
WORKDIR /app

COPY --from=dependencies /app /app

COPY src src

FROM openjdk:17-jdk-alpine
WORKDIR /app

COPY --from=build /app /app

#ARG PROFILE=default
#COPY application-${PROFILE}.properties /app/application.properties

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]
