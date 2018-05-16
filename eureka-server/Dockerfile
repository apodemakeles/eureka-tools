FROM java:8-jre-alpine

ADD target/eureka-server.jar /usr/local/micro-service/

EXPOSE 8761

WORKDIR /usr/local/micro-service/

ENTRYPOINT ["/usr/bin/java","-jar","eureka-server.jar"]