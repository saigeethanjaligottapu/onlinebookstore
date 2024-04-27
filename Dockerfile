FROM openjdk:11
EXPOSE 8083
ADD target/onlinebookstore.war onlinebookstore.war
ENTRYPOINT ["java","-jar","/onlinebookstore.war"]
