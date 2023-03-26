FROM openjdk:11-jdk
#ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} ./app.jar
COPY build/libs/FANFTASY-0.0.1-SNAPSHOT.jar ./app.jar
#EXPOSE 8099
ENTRYPOINT ["java", "-jar", "app.jar"]
