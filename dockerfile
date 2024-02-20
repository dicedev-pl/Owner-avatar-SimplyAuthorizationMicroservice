FROM khipu/openjdk17-alpine:latest
LABEL maintainer="simplyAuthMicroservice"
RUN mkdir jars && cd jars
WORKDIR /jars
COPY target/SimplyAuthorizationMicroservice-1.0.0.jar authentication.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "authentication.jar"]