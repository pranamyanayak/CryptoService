FROM openjdk:8-jdk
 
COPY /target/CryptoService-0.0.1-SNAPSHOT.jar /data/CryptoService/CryptoService-0.0.1-SNAPSHOT.jar
 
WORKDIR /data/CryptoService
 
RUN java -version
 
CMD ["java","-jar","CryptoService-0.0.1-SNAPSHOT.jar","server"]
 
EXPOSE 8080-8081
