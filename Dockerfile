# ---------- Stage 1: Build using Maven ----------
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy pom.xml and download dependencies (use cache efficiently)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the full project source
COPY src ./src

# Package the Spring Boot application (skip tests if not needed)
RUN mvn clean package -DskipTests

# ---------- Stage 2: Minimal JDK 21 runtime ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port (Spring Boot default is 8080)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
