# ===== Stage 1: Build the application =====
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build

# Set working directory
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Build the project
RUN mvn clean install -DskipTests

# ===== Stage 2: Run the application =====
FROM openjdk:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/myspringproject-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "app.jar"]
