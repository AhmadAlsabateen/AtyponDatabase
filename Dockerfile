FROM openjdk:8-jre
MAINTAINER Ahmad_Sabateen

EXPOSE 80

COPY target/AtyponDatabase-1.0-SNAPSHOT.jar /home/AtyponDatabase-1.0-SNAPSHOT.jar

CMD ["sh","-c","java -cp /home/AtyponDatabase-1.0-SNAPSHOT.jar server.Server"]

