FROM openjdk:11-slim
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000
ADD target/shop-0.0.1-SNAPSHOT.jar shop.jar
ENTRYPOINT ["java","-jar","shop.jar"]
