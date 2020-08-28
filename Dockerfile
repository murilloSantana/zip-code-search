FROM openjdk:13-alpine3.9
ARG JAR_FILE=application/target/*.jar
COPY ${JAR_FILE} zip-code-search.jar
ENTRYPOINT ["java","-jar","/zip-code-search.jar"]