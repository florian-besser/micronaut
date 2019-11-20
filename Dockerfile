FROM openjdk:13-alpine
COPY target/micronaut-0.1.jar /usr/src/micronaut/
WORKDIR /usr/src/micronaut

CMD ["java", "-Xmx1G", "-Xms1G", "-jar", "micronaut-0.1.jar"]
