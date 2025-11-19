# Build stage
FROM gradle:jdk25 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test -x spotlessCheck

# Runtime stage
FROM eclipse-temurin:25-jre-noble
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]