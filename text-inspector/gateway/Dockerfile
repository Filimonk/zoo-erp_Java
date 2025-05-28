FROM gradle:8.7-jdk21 AS builder
WORKDIR /app
COPY . /app
RUN gradle build --no-daemon

FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/app/build/libs/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

