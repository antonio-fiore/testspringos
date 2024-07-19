FROM openjdk:11-jre-slim
COPY testspringos-0.0.1-SNAPSHOT.jar /deployments/app.jar
ENTRYPOINT ["java", "-jar", "/deployments/app.jar"]

