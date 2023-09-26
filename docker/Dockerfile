FROM  openjdk:8u151-jdk-alpine3.7
ENV  APP_HOME  /usr/src/app
COPY target/APIServer-0.0.2-SNAPSHOT.war  $APP_HOME/APIServer-0.0.2-SNAPSHOT.war
EXPOSE 8080
WORKDIR $APP_HOME
ENTRYPOINT exec java -jar APIServer-0.0.2-SNAPSHOT.war
