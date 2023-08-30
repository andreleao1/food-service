# Food service

This project provides a REST API that return a simple object named Food. 
The main goal is use Redis like a cache using a TTL (Time to Live) of 10 seconds and only the first request 
to fetch food data should call the database, having to call again when pass the 10 seconds. 

## Required

To run this project is mandatory:

* Docker [Official documentation](https://www.docker.com/)
* Docker compose [Official documentation](https://docs.docker.com/compose/)

## How to run

To run this project is necessary clone the project using `git clone` command.

Before is necessary get inside the directory and use the following docker commands:

* `docker build -t food-service .`

* `docker compose up`

The service will be available in the *8080* port.

is possible using the service using the following URL:

Environment | URL
------------|----
Local       | http://localhost:8080/

Also is possible check if the service is up calling the following url to local environment [health check](http://localhost:8080/actuator/health)

## Swagger

Environment | URL
----------- | ---
Local       | http://localhost:8080/swagger-ui/index.html