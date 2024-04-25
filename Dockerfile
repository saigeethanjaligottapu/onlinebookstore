FROM openjdk
EXPOSE
ADD target/onlinebookstore.war  	onlinebookstore.war
ENTRYPOINT ["java","-jar","/onlinebookstore.war"] 
