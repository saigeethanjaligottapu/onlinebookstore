FROM openjdk:11
WORKDIR /onlinebookstore
COPY . /onlinebookstore/
EXPOSE 8082
ADD target/onlinebookstore.war onlinebookstore.war
ENTRYPOINT ["java","-jar","/onlinebookstore.war"]
