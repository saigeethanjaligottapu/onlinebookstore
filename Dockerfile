
FROM openjdk:11 AS builder

WORKDIR /onlinebookstore

COPY . /onlinebookstore/

RUN apt-get update && apt-get install -y maven

RUN mvn package  # Build the JAR file using Maven (adjust for your build tool)

# Stage 2: Tomcat image with WAR file (final stage)
FROM tomcat:latest

# Copy the WAR file from the builder stage
COPY --from=builder /onlinebookstore/target/onlinebookstore.war /usr/local/tomcat/webapps/onlinebookstore.war

EXPOSE 8080

ENTRYPOINT ["catalina.sh", "run"]

