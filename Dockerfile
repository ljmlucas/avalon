FROM maven:3.6.1-jdk-8-slim as build

WORKDIR /build

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src/ /build/src/

RUN mvn package


FROM openjdk:8-jre-alpine

COPY --from=build /build/target/avalon-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]