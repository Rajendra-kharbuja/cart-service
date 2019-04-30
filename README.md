# cart-service
a simple cart service as a demonstration of a REST microservice

## Build
To build:
- gradle:
```
$ gradle build
```
- gradle wrapper:
```
$ ./gradlew build
```

##Setup
To Run locally, first we need to run `docker-compose` to set up local postgreSQL instance:
```
$ docker-compose up
```

##Run
- gradle:
```
$ gradle clean bootRun
```
- gradle wrapper:
```
$ ./gradlew clean bootRun
