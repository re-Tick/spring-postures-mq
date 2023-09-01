# Sample Java 17, Springboot, postgres, rabbitmq sample app

### Setup
Run Postgres and RabbitMQ
```shell
sudo docker network create some-network

sudo docker run -it --rm --network some-network -p5432:5432 --name some-postgres -e POSTGRES_PASSWORD=mysecretpasswor postgres:10

# create notesDB
sudo docker run -it --rm --network some-network postgres:10 psql -h some-postgres -U postgres 

# then run CREATE DATABASE notesdb;


sudo docker run  --hostname my-rabbit --name some-rabbit -p5672:5672 rabbitmq:3 
```

### Run the application
```shell
# compile
mvn clean install 

java -jar target/spring-postgres-mq-0.0.1-SNAPSHOT.jar
```

### Usage

```shell
curl -X GET http://localhost:8080/api/notes

curl -X POST -H "Content-Type: application/json" -d '{"title":"Sample Title", "content":"Sample Content"}' http://localhost:8080/api/notes

curl -X GET http://localhost:8080/api/notes

curl -X DELETE http://localhost:8080/api/notes/
 
curl -X GET http://localhost:8080/api/notes
 
```