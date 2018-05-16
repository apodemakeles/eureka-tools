FROM java:8-jre-alpine

ADD target/eureka-tools.eureka-server.jar /usr/local/micro-service/

EXPOSE 8761

WORKDIR /usr/local/micro-service/

CMD ["/usr/bin/java","-jar","eureka-tools.eureka-server.jar"]