FROM openjdk:11
COPY ./build/libs/Mediscreen-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=container", "Mediscreen-0.0.1-SNAPSHOT.jar"]