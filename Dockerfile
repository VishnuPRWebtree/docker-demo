FROM eclipse-temurin:21-jre-jammy

LABEL maintainer="docker-demo-app"
LABEL version="1.0"
LABEL description="Spring Boot Docker Demo Application"

WORKDIR /app

COPY target/demo-0.0.1-SNAPSHOT.jar /app/docker-demo.jar

# Expose the port your application runs on
EXPOSE 8081

# Use array syntax for better argument handling
ENTRYPOINT ["java", "-jar", "docker-demo.jar"]