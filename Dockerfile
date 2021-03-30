FROM openjdk:14-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} top.jar
ENTRYPOINT ["java","-jar","/top.jar"]
