FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} forex-service.jar
ENTRYPOINT ["java","-jar","/forex-service.jar"]