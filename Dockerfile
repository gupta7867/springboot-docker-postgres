# Step 1: Use a Java runtime base image
FROM openjdk:17-jdk-slim

# Step 2: Set working directory
WORKDIR /app

# Step 3: Copy the JAR file (replace target/*.jar with your JAR)
COPY target/*.jar app.jar

# Step 4: Expose port
EXPOSE 9090

# Step 5: Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
