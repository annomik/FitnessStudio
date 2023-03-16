FROM eclipse-temurin:17-jre-focal
MAINTAINER HannaM

ADD ./target/fitness_studio.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/fitness_studio.jar"]

EXPOSE 8080 8089