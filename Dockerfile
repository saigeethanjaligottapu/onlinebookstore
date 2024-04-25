FROM openjdk
EXPOSE 8080
ADD target/onlinebookstore.war  onlinebookstore.war
ENTRYPOINT ["java","-jar","/onlinebookstore.war"] 
