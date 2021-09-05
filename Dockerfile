FROM openjdk:8-jre
MAINTAINER Ahmad_Sabateen

COPY target/AtyponDatabase-1.0-SNAPSHOT.jar /home/AtyponDatabase-1.0-SNAPSHOT.jar

CMD ["java","-jar","/home/AtyponDatabase-1.0-SNAPSHOT.jar"]

