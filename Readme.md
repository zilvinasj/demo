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

### Running the application
 
Run Tests & Checks:

``mvn clean install``

Run Application:

If you want to start fresh don't change anything, if you want some pre-populated data rename `rename.me` to `data.sql`

1. ``mvn spring-boot:run``

2. Navigate to http://localhost:8080/swagger-ui.html to see all available endpoints

3. Navigate to http://localhost:8080/h2-console to see persistent data