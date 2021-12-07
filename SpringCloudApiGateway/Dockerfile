FROM openjdk:11-jre-slim
WORKDIR usr/src
EXPOSE 9000
ADD ./target/SpringCloudApiGateway-0.0.1-SNAPSHOT.jar /usr/src/SpringCloudApiGateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","SpringCloudApiGateway-0.0.1-SNAPSHOT.jar"]
