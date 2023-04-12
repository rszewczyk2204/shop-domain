FROM maven:3.6.0-jdk-11-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f service/pom.xml clean package -DskipTests=true

FROM eclipse-temurin:11-jdk-alpine
COPY service/shop-domain-service/target/shop-domain-service.jar /shop-domain-0.0.1-SHAPSHOT.jar
RUN mvn -f /service/pom.xml clean package -DskipTests=true
ENTRYPOINT ["java","-jar","/shop-domain-0.0.1-SHAPSHOT.jar"]
EXPOSE 8880