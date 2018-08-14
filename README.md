# project-diff

A minimal REST API for finding differences between two versions of data.

## Tech Stack
- Spring Boot 2
- Java 8
- HSQLDB (in-memory database)
- Maven 3
- Swagger 2

## System Requirements
- JDK 8+
- Maven 3.3+

## Build and Run
Build, run tests and verify the package via console with:

`mvn clean verify`

To run the application locally via console:

`mvn spring-boot:run` 

## Documentation

After run the application locally, you can see and try out the endpoints from that address:

`http://localhost:8080/v1/swagger-ui.html`

## Improvements
- Add request and business logic validations
- A NoSQL database such as MongoDB could be used as storage instead of a relational database.
- AWS S3 also could be used as storage instead of a relational database.