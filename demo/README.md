# ticket-booking

## Run

to run application:

`mvn spring-boot:run`

and then to test it:

`curl "http://localhost:8080/bookings/bookings`
(get all bookings)

### Swagger
1. use the url, in case server port is not changed
   `http://localhost:8080/webjars/swagger-ui/index.html`
### OpenAPI docs
1. use the url, in case default server port is used
   `http://localhost:8080/v3/api-docs`

## Tests

to run all tests:

`mvn test`

## TODO

- include security
- integrate with UI

## Requirements

The goal is to build a container booking system.
### Business scenario (use case)
1. The user checks for availability of a container with required specifications.
2. The system returns whether the container of specified configurations is available for booking.
3. If the container is available, then the user can place the request for booking. Once the booking is successful, the booking reference id is returned to the user.

### Assumptions
1. The details customer and their corresponding booking is stored.
2. Customer verification is done before onboarding.
3. The MongoDb is installed local in the server using port 27017
### Business requirements
1. The data in the external system should be valid.
2. The inventory should be updated on successful booking of a container by a customer.
### Technical requirements
1. Application must be written in JVM language (Java, React)
2. Operations must be exposed as REST services
3. Mongo db used by backend
### Demo
1. Open the project in IDE.
2. Build the project using `maven clean install`.
3. Refer the postman collection to test the urls.


