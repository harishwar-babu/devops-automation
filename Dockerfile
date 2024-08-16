FROM openjdk:17
EXPOSE 8965
ADD target/online-banking.jar online-banking.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/online-banking.jar"]