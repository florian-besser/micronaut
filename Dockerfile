FROM openjdk:13-alpine
COPY target/micronaut-0.1.jar /usr/src/greeter/
WORKDIR /usr/src/greeter

CMD ["java", "-Xmx16G", "-Xms16G", "-jar", "micronaut-0.1.jar"]
