## Microservices of two Java processes

#### 1. Application Process (Server)

Runs on: http://localhost:8000/api/feedbacks

#### 2. Data Injector Process (client)

Runs on: http://localhost:8090/data-injector/generate

## Requirements

* [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or later
* [Maven 3.0+](http://maven.apache.org/download.cgi)

## Build with Maven

* [Welcome to Apache Maven](https://maven.apache.org/)
* [Building Java Projects with Maven](https://spring.io/guides/gs/maven/)

## Build and run tests with Maven

* cd into project-root-folder using the terminal.

* Run this maven command:
 
``` 
mvn clean test
``` 

## Run the services

* cd into project-root-folder using your terminal.

* Using Maven you can run the application using **mvn spring-boot:run**. 

```
1. Application Process (Server)
  cd microservices/feedback-service/feedback-service-application
  mvn spring-boot:run
```

* Or you can build an executable JAR file with **mvn clean package** and run the JAR by typing:

```
  java -jar microservices/feedback-service/target/feedback-service-1.0.0.jar
```

```
2. Data Injector Process (client)
  cd microservices/data-injector
  mvn spring-boot:run
```

* Or you can build an executable JAR file with **mvn clean package** and run the JAR by typing:

```
  java -jar microservices/data-injector/target/data-injector-1.0.0.jar
```

## Test the services

* Call this get request a few times, each call will create a new feedback with a 'pre_calc' state, which will be updated by a scheduler service (AlgoService):

```
GET http://localhost:8000/data-injector/generate
```

* Get all feedbacks:

```
GET localhost:8000/api/feedbacks
```

