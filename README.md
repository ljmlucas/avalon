#Build Package
./mvnw clean package

#Running with maven
./mvnw spring-boot:run

#Running Jar
java -jar target/avalon-1.0.0.jar

#Docker
docker build -t avalon .
docker run --rm -p 127.0.0.1:8080:8080  avalon

#Code Coverage Report
./mvnw clean test

Report is created at /target/site/jacoco/index.html


http://localhost:8080/swagger-ui.html