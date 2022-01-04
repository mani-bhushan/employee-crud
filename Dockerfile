FROM openjdk:8
EXPOSE 8889
ADD target/employee-crud-docker.jar employee-crud-docker.jar
ENTRYPOINT ["java", "-jar", "/employee-crud-docker.jar"]