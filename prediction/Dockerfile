FROM openjdk:11-jre-slim
COPY target/prediction-0.0.1-SNAPSHOT.jar /app/prediction.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app/prediction.jar"]