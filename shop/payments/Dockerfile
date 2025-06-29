FROM gradle:8.14-jdk21 AS builder
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/app/build/libs/payments.jar payments.jar
ENTRYPOINT ["java", "-jar", "payments.jar"]

