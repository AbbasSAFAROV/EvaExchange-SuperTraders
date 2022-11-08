FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY build/libs/*.jar app.jar
EXPOSE 80
CMD ["java","-jar","app.jar"]