FROM ubuntu:latest
FROM openjdk:11.0.16-jdk
COPY service/shop-domain-service/target/shop-domain-service.jar /shop-domain-0.0.1-SHAPSHOT.jar
ENTRYPOINT ["java","-jar","/shop-domain-0.0.1-SHAPSHOT.jar"]
EXPOSE 8880