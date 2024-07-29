FROM maven:3-amazoncorretto-21 AS build
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:21-jdk-slim
COPY --from=build /target/rentalPlaces-0.0.1.jar rentalPlaces.jar
EXPOSE 8080
ENTRYPOINT["java", "-jar","rentalPlaces.jar"]