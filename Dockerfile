FROM openjdk
EXPOSE 8080
ADD  	targesst/onlinebookstore.war  onlinebookstore.war
ENTRYPOINT ["java","-jar","/onlinebookstore.war"] 
