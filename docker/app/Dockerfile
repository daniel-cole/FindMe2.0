FROM tomcat:9
RUN rm -rf /usr/local/tomcat/webapps/*
COPY findme-0.1.0.war /usr/local/tomcat/webapps/ROOT.war
ENV JAVA_OPTS="-Dspring.config.location=file:/config/application.properties"
