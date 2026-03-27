# Stage 1:Build using Maven
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy project files
COPY . .

# Build jar
RUN mvn clean package -DskipTests

# Stage 2: Run using lightweight image
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy only jar and builder stage
COPY --from=builder /app/target/employee-management-system.jar app.jar

# Expose port
EXPOSE 8080 

# Run application
ENTRYPOINT ["java","-jar","app.jar"]