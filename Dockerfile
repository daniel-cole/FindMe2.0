FROM openjdk:8-jdk-alpine
ARG FINDME_JAR
VOLUME /tmp
COPY src/main/resources/application.properties /config/application.properties
ADD $FINDME_JAR app.jar
ENV JAVA_OPTS="-Dspring.config.location=file:/config/application.properties"
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar
