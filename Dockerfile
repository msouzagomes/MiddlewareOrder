FROM openjdk:11-jre-slim
WORKDIR /usr/app
COPY /target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/usr/app/app.jar"]
