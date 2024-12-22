## Spring Data OpenSearch

#### Description

The application is using Spring Data OpenSearch for indexing and search capabilities of OpenSearch.

## Demo Video (Running the application)

[Video](https://share.vidyard.com/watch/D3XMg1zZFtNEyhm8DWx79B?)

## Requirements

* [JDK 21](https://www.oracle.com/il-en/java/technologies/downloads/)
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

## Install and Start OpenSearch

* Start an OpenSearch Instance, and opensearch-dashboards, by running the command:

```shell
docker-compose up
```

* Or start an OpenSearch Instance by running the Docker `network` and `run` commands:

```shell
docker network create opensearch-network
# This command maps ports 9200 and 9600, sets the discovery type to "single-node" and requests the newest image of OpenSearch
docker run -d --name opensearch -p 9200:9200 -p 9600:9600 -e "discovery.type=single-node" -e "OPENSEARCH_INITIAL_ADMIN_PASSWORD=Wreckit2000$" --net opensearch-network --restart=always opensearchproject/opensearch:latest
```

## Run the application

* cd into project-root-folder using your terminal.

* Using Maven you can run the application using **mvn spring-boot:run**.
  Or you can build an executable JAR file with **mvn clean package** and run the JAR by typing:

```
  java -jar target/opensearch-0.0.1-SNAPSHOT.jar
```

## Routes

Based on CRUD operations, the following routes are available:

```
Create Index:  POST localhost:8080/api/posts/index
Create Post:   POST localhost:8080/api/posts
Read Post:     GET localhost:8080/api/posts/vu1L7ZMB4V5b76-0zItJ
List Posts:    GET localhost:8080/api/posts
```

## Test the application - make these API requests

* Create Index 
(Optional because @Document has createIndex = true, so if the index doesn't exist, it will be created automatically)
```
POST localhost:8080/api/posts/index
{              
	"name" : "postindex"
}
```
* Delete Index
```
Delete localhost:8080/api/posts/index/[INDEX_NAME]
```
* Index Exists
```
Get localhost:8080/api/posts/index/[INDEX_NAME]
```
* Create Post:
```
POST localhost:8080/api/posts
{              
    "name" : "Post1",
    "description" : "My post 1",
    "amount" : 1
}
```
* Read Post:
```
GET localhost:8080/api/posts/[POST_ID]
```
* List Posts:
```
GET localhost:8080/api/posts
```
## More commands

* List all indexes in OpenSearch by using docker exec, and curl command:
```
curl http://localhost:9200/_aliases
```

* Delete an index in OpenSearch by using docker exec, and curl command:
```
curl -X DELETE http://localhost:9200/postindex
```