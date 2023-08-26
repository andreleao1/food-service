FROM maven:3.8.5-openjdk-17-slim as builder

COPY /src /app/src
COPY /pom.xml /app

RUN mvn -f /app/pom.xml clean package -DskipTests

FROM amazoncorretto:17-alpine

COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]