FROM tomcat:9
ARG WAR_FILE
RUN echo $WAR_FILE
RUN rm -rf /usr/local/tomcat/webapps/*
COPY $WAR_FILE /usr/local/tomcat/webapps/ROOT.war
ENV JAVA_OPTS="-Dspring.config.location=file:/config/application.properties"
