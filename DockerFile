FROM java:8
MAINTAINER Anderson Silva
EXPOSE 8080
ADD /target/tcp-1.0.0-SNAPSHOT.jar tcp-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "tcp-1.0.0-SNAPSHOT.jar"]