# run database

pull mysql in docker hub

docker pull mysql

-start mysql server

1. docker run --name restaurant-service-db -e MYSQL_ROOT_PASSWORD=12345678 -p 3307:3306 -d mysql:latest

2. docker exec -it restaurant-service-db bash

3. mysql -u root -p

   enter your password

4. create dabase restaurant_service_database_v1

# how to pull and run kafka

pull kafka in docker hub

docker pull bitnami/kafka:3.7.0

-start kafka

1. access to folder root

2. docker-compose up -d

## note

to run restaurant backend service, first of all how have to run:

- mysql image
- kafka image
