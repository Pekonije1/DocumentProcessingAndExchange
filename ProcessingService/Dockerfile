FROM gradle:8.10.0-jdk21-alpine AS build
WORKDIR /app

COPY ../ .

RUN gradle bootJar

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/build/libs/*.jar processingservice.jar

EXPOSE 8080

CMD ["java", "-jar", "processingservice.jar"]
