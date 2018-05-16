FROM java:8-jre-alpine

ADD target/eureka-tools.mock-client.jar /usr/local/micro-service/

EXPOSE 8080

WORKDIR /usr/local/micro-service/

ENTRYPOINT ["/usr/bin/java","-jar","eureka-tools.mock-client.jar"]