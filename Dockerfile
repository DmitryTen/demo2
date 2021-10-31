FROM openjdk:11

RUN mkdir -p /opt/app/config

COPY ./target/vimpelcom-*-SNAPSHOT.jar /opt/app/app.jar
COPY ./target/classes/application.yml /opt/app/config/

CMD ["java", "-jar", "/opt/app/app.jar"]


