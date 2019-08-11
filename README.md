# AVALON

##### Requirements

For building and running the application you need:

- [JDK 1.8]

# Running the application locally with maven

- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml

```shell
./mvnw spring-boot:run
```

- Visit http://localhost:8080/swagger-ui.html for documentation

# Build an executable JAR

- Open Command Prompt and Change directory (cd) to folder containing pom.xml

```shell
./mvnw clean package
```

- Then you can run the JAR file:

```shell
java -jar target/avalon-1.0.0.jar
```

# Running the application locally with DOCKER

##### Requirements
 - [Docker] https://docs.docker.com/install/ 
 
 ```shell
docker build -t avalon .
docker run --rm -p 127.0.0.1:8080:8080  avalon
```

- Visit http://localhost:8080/swagger-ui.html for documentation

# How to consume the API

- Apart from the documentation page, the endpoints are secured by a JWT token:


- List Transactions: **/transactions**
- List Transactions by Type: **/transactions/{type}**
- Get Total Amount of Transactions by Type: **/transactions/{type}/total-amount**


- Create a POST request with URL http://localhost:8080/login. The body should have a valid username and password. In this project, the username is admin and the password is password. 

 ```shell
curl http://localhost:8080/login -v  \
	--request POST \
	--header "Content-Type: application/json" \
	--data '{"username":"admin","password":"password"}'	
```

- Copy the header Authorization from the response header, the token is valid for 24 hours:

 ```
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU2NTYyNzIxOX0.46P_Vro3CX6kYuOY2oabH0VV8Oqvodt1G0vftOKeFCu9DY1lZ3PXY8GiLmSzxCQx1yNPF6ANrAYq7jlWj9uUHg
```

- Access endpoints with header Authorization: $token

```shell
curl http://localhost:8080/transactions \
	--request GET \
	--header "Content-Type: application/json" \
	--header "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU2NTYyNzIxOX0.46P_Vro3CX6kYuOY2oabH0VV8Oqvodt1G0vftOKeFCu9DY1lZ3PXY8GiLmSzxCQx1yNPF6ANrAYq7jlWj9uUHg"
```
 

# Code Coverage Report

- Open Command Prompt and Change directory (cd) to folder containing pom.xml

 ```shell
./mvnw clean test
```
- Visit /target/site/jacoco/index.html to check code coverage http://localhost:8080/swagger-ui.html