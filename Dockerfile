# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from the host into the container
COPY target/*.jar app.jar

# Expose port 8080 to the outside world
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]

