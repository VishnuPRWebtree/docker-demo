FROM eclipse-temurin:21

LABEL mentainer="doker-demo-app"

WORKDIR /app

COPY target/demo-0.0.1-SNAPSHOT.jar /app/docker-demo.jar

ENTRYPOINT ["java", "-jar", "docker-demo.jar"]
