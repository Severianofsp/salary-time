FROM gradle:7.6.4-jdk17-alpine AS builder
WORKDIR /app
COPY build.gradle settings.gradle /app/
COPY src /app/src/
RUN gradle build --no-daemon

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]