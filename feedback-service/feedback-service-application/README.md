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

## Install and Start OpenSearch and OpenSearch Dashboards

* Start an OpenSearch instance and OpenSearch Dashboards by running the command:

```shell
docker-compose up
```

## Install and Start OpenSearch (Alternative)
* start an OpenSearch Instance by running the Docker `network` and `run` commands:

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

## Routes (API Endpoints)

* Create index
```
POST localhost:8000/api/feedbacks/index
{              
	"name" : "feedbackindex"
}
```
###### Note: Create index is optional because @Document has createIndex = true, so if the index doesn't exist, it will be created automatically.

* Delete index
```
Delete localhost:8000/api/feedbacks/index/feedbackindex
```
* Index exists
```
Get localhost:8000/api/feedbacks/index/feedbackindex
```
* Create feedback:
```
POST localhost:8000/api/feedbacks
{              
    "name" : "Feedback1",
    "description" : "My feedback 1",
    "amount" : 1
}
```
* Read feedback:
```
GET localhost:8000/api/feedbacks/[FEEDBACK_ID]
```
* List feedbacks:
```
GET localhost:8000/api/feedbacks
```
## OpenSearch Dashboards

http://localhost:5601/app/dev_tools#/console

* Create Index

```
PUT /feedbackindex
{
  "settings": {
    "index": {
      "number_of_shards": 2,
      "number_of_replicas": 1,
      "max_ngram_diff": 50
    },
    "analysis": {
      "filter": {
        "ngram_filter": {
          "type": "ngram",
          "min_gram": 1,
          "max_gram": 50
        }
      },
      "analyzer": {
        "ngram_analyzer": {
          "type": "custom",
          "tokenizer": "standard",
          "filter": ["lowercase", "ngram_filter"]
        }
      }
    }
  },
  "mappings": {
    "properties": {
      "title": {
        "type": "text",
        "analyzer": "ngram_analyzer"
      }
    }
  }
}
```
*  Find index names, sorted
```
GET /_cat/indices?&s=i
```
*  Find index names, starts with feedbackindex
```
GET _cat/indices/feedbackindex*
```
*  Delete index
```
DELETE /feedbackindex
```
*  Analyze the text "bug" using the ngram_analyzer defined in the feedbackindex index
```
GET feedbackindex/_analyze
{
  "analyzer": "ngram_analyzer",
  "text": "bug"
}
```

## More commands

* List all indexes in OpenSearch by using docker exec, and curl command:
```
curl http://localhost:9200/_aliases
```

* Delete an index in OpenSearch by using docker exec, and curl command:
```
curl -X DELETE http://localhost:9200/feedbackindex
```