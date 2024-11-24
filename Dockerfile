# Build stage
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]