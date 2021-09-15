FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} library.jar
ENTRYPOINT ["java","-jar","/library.jar"]