FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY build/libs/nginx-test-be-0.0.1-SNAPSHOT.jar nginx-test-be.jar
COPY src/main/resources/application.yml application.yml
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "nginx-test-be.jar"]