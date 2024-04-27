FROM openjdk:11
ARG JAR_FILE=target/*.war
COPY ${WAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
