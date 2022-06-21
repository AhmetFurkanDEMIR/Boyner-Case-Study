FROM openjdk
WORKDIR /
ADD BoynerCase.jar BoynerCase.jar
EXPOSE 8080
CMD ["java", "-jar", "BoynerCase.jar"]