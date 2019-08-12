FROM adoptopenjdk/openjdk11:alpine-slim

COPY taxes-service-ws/target/taxes-service-ws-*.jar /app/taxes-service-app.jar

CMD ["java", "-jar", "/app/taxes-service-app.jar"]
