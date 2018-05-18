FROM java:8-jre-alpine

ADD target/zuul-router.jar /usr/local/micro-service/

EXPOSE 8080

WORKDIR /usr/local/micro-service/

ENTRYPOINT ["/usr/bin/java","-jar","zuul-router.jar"]