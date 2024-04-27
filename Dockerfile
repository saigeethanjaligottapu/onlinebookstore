FROM openjdk:11
ARG JAR_FILE=target/*.war
COPY ${WAR_FILE} app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app.jar"]
