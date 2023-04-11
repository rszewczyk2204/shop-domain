FROM eclipse-temurin:11-jdk-alpine
COPY target/shop-domain-0.0.1-SNAPSHOT.jar /shop-domain-0.0.1-SHAPSHOT.jar
ENTRYPOINT ["java","-jar","/shop-server-0.0.1-SHAPSHOT.jar"]
EXPOSE 8880