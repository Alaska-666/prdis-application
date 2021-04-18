FROM openjdk:11-jre-slim
COPY target/currency-0.0.1-SNAPSHOT.jar /app/currency.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/currency.jar"]