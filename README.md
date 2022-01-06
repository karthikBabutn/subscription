# Subscription Service

## Installation

Use Maven to build the application

```bash
mvn clean install
```

## Test case

Use Maven to run the testcase

```bash
mvn clean test
```

## Running

Upon successfully packaging the app, Use the command to start the application

```bash
mvn spring-boot:run
```
## Other Information

**Health** : end point used to determine whether the service is Up or Not
   [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)



**Swagger**: endpoint used to determine the API end point and the response and error models. You can execute the application from within the swagger end point by clicking on "Try It Out".
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   

**Metrics**:end point used to determine the metrics of the application and the environment statistics associated with it.
   [http://localhost:8080/actuator/metrics](http://localhost:8080/actuator/metrics)
* For Specific metrics, like http.server.requests, append the same to the url above.


**API end point**:
The API end point can be found in Swagger documentation.
* [http://localhost:8080/api/v1/subscription/create](http://localhost:8080/api/v1/subscription/create)
