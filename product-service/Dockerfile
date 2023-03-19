FROM eclipse-temurin:17-jre-focal
MAINTAINER HannaM

ADD ./target/product-service-3.0.2.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/product-service-3.0.2.jar"]

EXPOSE 8080 8089