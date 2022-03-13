FROM openjdk:11-jdk

VOLUME /tmp

ARG JAR_FILE=./build/libs/comebackhome-0.0.1.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["nohup","java","-jar","-Dspring.profiles.active=prod","app.jar","2>&1","&"]

