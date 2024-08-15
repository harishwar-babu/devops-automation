FROM openjdk:17
EXPOSE 8965
ADD target/online-banking.jar online-banking.jar
ENTRYPOINT ["java","-jar","/online-banking.jar"]