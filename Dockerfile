FROM openjdk:11-jre
COPY target/testspringos-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

