FROM openjdk

EXPOSE 8080

WORKDIR /app

COPY target/labseq-0.0.1-SNAPSHOT.jar /app/labseq.jar

ENTRYPOINT ["java", "-jar", "labseq.jar"]