FROM openjdk:8-jre
ADD target/itoken-oauth2-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8771
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

