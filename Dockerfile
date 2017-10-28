FROM tomcat:9
ARG DOCKER_WAR_FILE 
RUN rm -rf /usr/local/tomcat/webapps/*
COPY $DOCKER_WAR_FILE /usr/local/tomcat/webapps/ROOT.war
ENV JAVA_OPTS="-Dspring.config.location=file:/config/application.properties"
