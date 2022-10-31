FROM openjdk:11
ADD target/cloud-config-server-1.0.jar cloud-config-server-1.0.jar
EXPOSE 9296
ENTRYPOINT ["java", "-jar", "/cloud-config-server-1.0.jar"]