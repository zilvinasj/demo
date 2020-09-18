FROM openjdk:11.0.7-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} top.jar
ENTRYPOINT ["java","-jar","/top.jar"]
