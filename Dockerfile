# Start with base image
FROM openjdk:11
# Add Maintainer Info
LABEL maintainer="rushikeshpatil"
# Add a temporary volume
VOLUME /tmp
# Expose Port 8083
EXPOSE 8083
# Application Jar File
ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar
# Add Application Jar File to the Container
ADD ${JAR_FILE} demo.jar
# Run the JAR file
ENTRYPOINT ["java", "-jar", "/demo.jar"]