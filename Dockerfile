# Stage 1: Build
FROM maven:3-amazoncorretto-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/rentalPlaces-0.0.1.jar rentalPlaces.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "rentalPlaces.jar"]
