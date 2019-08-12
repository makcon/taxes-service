# Implementation of taxes service

`taxes-service` is a web service which allows to calculate product taxes. The service is written in Java and based on spring-boot framework. Maven is using to building the project. The service contains 3 modules:
- `taxes-service-client`: this is a java client which can be imported and used in another java application for simple communication with the service. 
- `taxes-service-common`: common module which contains DTOs, constants and other objects needed for `client` ans `ws` modules.
- `taxes-service-ws`: REST web service.

## How to build the service

- Clone or download the repository.

#### Using an IDE

- Open the project in your favorite IDE (e.q. Intellij Idea).
- Run spring-boot `Application` class of `taxes-service-ws` module. The service will be running on port `8080`.

#### Using command line

- Build the project using `mvn clean package`.
- If you use `Docker` simply run `docker-compose up`.
- Or start the service: `java -jar taxes-service-ws/target/taxes-service-ws-1.0-SNAPSHOT.jar`.


## How to use the service

1. Open swagger-api ui: `http://localhost:8080/swagger-ui.html`.
2. Here you can see all available API endpoints and models and try to execute examples.
3. There is also simple (but beautiful) UI available by address: `http://localhost:8080`.

## HTTP java client

If you need to use the service from another java program, you can use client provided by the service. The usage is pretty simple. Just build and import maven module `taxes-service-client`. If your service is using Spring you need to import the module in your configuration: `@Import(TaxesServiceClientConfig.class)` and override the service base url if necessary: `-Dservice.base.url=YOUR_SERVICE_URL`. If you use plain java program you can create the client by your self. See a usage example in `TaxesServiceClientUsage`. Also using the example you can try how the service works. Just open it in your IDEA and run `main` method (Note: the service should be running).

### TODO list

- Add requests validation.
- Add errors handling.
- There is `ProductController` which allows requesting all available products. In the real world, all this functionality should be moved to a separate service if we are talking about microservices architecture. Also, the service doesn't need to receive the whole `Product` object. It's enough to know product id only and then the service can request all needed information (like `ProductCategory`) from product service. 
