FROM eclipse-temurin:17.0.11_9-jre-ubi9-minimal
COPY target/Idea_Y-0.0.1-SNAPSHOT.jar /idea.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/idea.jar"]