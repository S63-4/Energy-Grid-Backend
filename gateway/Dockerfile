FROM openjdk:13
ADD target/gateway.jar gateway.jar
EXPOSE 8762
ENTRYPOINT ["java","-jar","gateway.jar"]