FROM eclipse-temurin:11-jdk-alpine
COPY service/shop-domain-service/target/shop-domain-service.jar /shop-domain-0.0.1-SHAPSHOT.jar
ENTRYPOINT ["java","-jar","/shop-domain-0.0.1-SHAPSHOT.jar"]
EXPOSE 8880