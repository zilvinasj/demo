# Demo

## Application features

Interface can be seen under /swagger-ui.html endpoint

### Technologies used

* Spring Boot (Web, Feign, JPA)
* H2
* Caffeine
* Lombok
* WireMock
* JUnit

### Required dependencies to run the project

* Maven (download from https://maven.apache.org/download.cgi)

* JDK 11 (download from https://adoptopenjdk.net/?variant=openjdk11)

### Steps to run the application without docker compose

* Navigate to the root of the project

* Run `mvn spring-boot:run`

* API Swagger docs will become available at `http://localhost:8080/swagger-ui.html`

### Steps to run the application with docker compose

* Navigate to the root of the project

* Run `mvn clean install`

* Run `docker-compose build`

* Run `docker-compose up`

* API Swagger docs will become available at `http://localhost:8080/swagger-ui.html`
