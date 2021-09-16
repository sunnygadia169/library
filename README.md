# Book Library

## Description ## 
Library is a solution for online book selling system where customers can be onboarded, books can be added, orders can be placed.

## Tech Stack ##
* Java 11
* Spring Boot 2.5.4
* MongoDB 4.2.15
* Springfox for Swagger 2.6.1
* Docker

## Steps to bring up the application with docker container ##
* Assumption - JDK 11, Docker, Maven are already installed
* Create a jar for the application
    * $ ./mvnw clean install -D skipTests
* Build the docker image and bring up the app
    * $ docker-compose build
    * $ docker-compose up

## Swagger UI ##
* http://localhost:8080/swagger-ui.html
* http://192.168.99.100:8080/swagger-ui.html - If the app is up with container. The host IP is the IP of the virtual box for the docker-machine.

## Build and Launch files for Eclipse IDE ##
* LIRBRARY_APP_CLEAN_INSTALL.launch - This will run mvn clean install for the project
* LIBRARY_APP_LAUNCH.launch - This will launch the library application

## Postman Collection ##
* PostmanCollection/LibraryTestingCollection.postman_collection.json - Postman collection with sample requests when app up on localhost
* PostmanCollection/LibraryTestingDockerIP.postman_collection.json - Postman collection with sample requests when app is up in a docker container. The host IP here is the IP of the virtual box for docker-machine

## MONGO DB Local Configuration  ##
* Default port - 27017
* Database name - libraryDataBase


