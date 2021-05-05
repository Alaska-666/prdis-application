FROM openjdk:11-jre-slim
COPY target/weather-0.0.1-SNAPSHOT.jar /weather.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/weather.jar"]