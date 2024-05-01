FROM maven:3.8.4-jdk-8 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar ./app.jar
COPY src/main/webapp ./src/main/webapp
CMD ["java", "-jar", "app.jar"]