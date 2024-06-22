FROM eclipse-temurin:17.0.11_9-jre-ubi9-minimal
WORKDIR /app
COPY target/*.jar /app/idea.jar
ENTRYPOINT ["java", "-jar", "/idea.jar"]