FROM sapmachine:jdk-ubuntu-17.0.8
COPY target/bulletinboard-ads-0.0.2-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]