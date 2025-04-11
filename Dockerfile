FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/credito-api-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"