## Overview
URL Shortening is a technique in which URL may be made substantially shorter and still direct to the required page.<br /> 
This is achieved by using a redirect which links to the web page that has a long URL.<br /> 
For example, the URL https://en.wikipedia.org/wiki/Portal:Current_events/Sports can be shortened to https://my.tiny/dKrKSB.<br />

For more details, please check [requirements](architecture/requirment.md) and [architecture](architecture/architecture.png).<br />

### Prerequisites
If you want to run locally, make sure you have installed all of the following prerequisites on your development machine:
* Git - [Install Git](https://git-scm.com/downloads)
* Java21 - [Install Java21 JDK](https://openjdk.org/projects/jdk/21/)
* Maven - [Install Maven](https://maven.apache.org/install.html)
* Docker - [Install Docker Engine](https://docs.docker.com/engine/install/) (AWS LocalStack docker container is used for local testing)

### Run Application Locally
From the command line, navigate to the root directory and execute the script `./scripts/run-locally.sh`.

### Deploy CloudFormation Stack to AWS 
Add AWS access key and secret in `~/.aws/credentials` file.<br />
From the command line, navigate to the root directory and execute the script `./scripts/deploy-all.sh`.

### Delete CloudFormation Stack from AWS
From the command line, navigate to the root directory and execute the script `./scripts/delete-all.sh`.

### Swagger
Swagger URL pattern is `https://{{host}}/swagger.html`.<br />
Here is the link when running Spring Boot locally: http://localhost:8080/swagger.html

### API Samples

#### Request URL Shortening
POST http://localhost:8080/v1/shorten<br />
Body: 
```json
{
  "url": "https://en.wikipedia.org/wiki/Portal:Current_events/Sports",
  "owner": "wikipedia"
}
```
Response:
```json
{
  "shortUrl": "http://localhost:8080/dKrKSB"
}
```

#### Navigate Using Short URL
GET http://localhost:8080/dKrKSB (user will be redirected to https://en.wikipedia.org/wiki/Portal:Current_events/Sports)


