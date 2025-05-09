# Microservices with Spring boot

Example app microservices patter with Java 21 using:

- Microservices patter
- Hexagonal architecture 
- Cache aside patter
- Data base per service patter

- Spring Boot
- Spring data
- Spring AOP
- Memory Data save with caffeine
- Spring Webflux
- Spring validation

### Hello!

This repository is created for technical test for tekton

# Example microservice apps

1. [Microservices Calculation](/microservices-calculation) This service is responsible for performing a calculation,
two parameters are sent which are added and a percentage is calculated which is consulted in an external service,
then a record of the request is sent as an audit which is stored , Several spring implementations have been carried
 out and can be consulted in the following links by [Spring Projects](https://spring.io/projects)
2. [Microservices call history](/microservices-call-history) This service is responsible for storing the queries made as
 an auditory, which is stored for later consultation in paginated form.

## Architecture and folder distribution

    microservices-calculation/
    ├── pom.xml
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/
    │   │   │       └── tektontest/
    │   │   │           ├── application/
    │   │   │           │      └── dto/
    │   │   │           │      └── exception/
    │   │   │           │      └── services/
    │   │   │           ├── domain/
    │   │   │           │      └── exception/
    │   │   │           │      └── mapper/
    │   │   │           │      └── model/
    │   │   │           │      └── port/
    │   │   │           └── infrastructure/
    │   │   │                  └── adapter/
    │   │   │                  └── config/
    │   │   │                  └── shared/
    │   │   └── resources/
    │   │       ├── application.properties
    │   │       └── plantillas/
    │   └── test/
    │       ├── java/
    │       │   └── com/
    │       │       └── ejemplo/
    │       │           └── pruebas/
    │       └── resources/
    └── target/


## How to start

In the docker compose files, you have the database images as well as the remote docker hub repositories.

The easiest way is to use `docker-compose`:

```
docker-compose up -d
```

We have a collection of the requests necessary for the operation of the applications.

## Contribution

Project oriented as a base test, for the implementation of microservices with technologies based on the spring framework
and spring boot ecosystem.