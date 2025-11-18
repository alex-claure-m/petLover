# levanto el java-maven del cual va a tomar el proyecto para construir el jar
FROM maven:3.8.6-openjdk-17 AS build
# remarco el directorio del trabajo
WORKDIR /app
# le digo que COPIE las dependencias del POM - que copie los archivos de las dependencias
COPY pom.xml .
# y levanta todas las dependencias de manera offline
RUN mvn dependency:go-offline
# copia todo el proyecto
COPY src ./src
# levanta el proyecto y correlo
RUN mvn clean package -DskipTest
# verifico el archivo .jar se haya generado
RUN ls -la /app/target

# esto es para cuando ya genere el .jar
FROM openjdk:17-jdk-alpine

WORKDIR /app
# y copio y cambio el nombre el mismo .jar
COPY --from=build /app/target/petLover-0.0.1-SNAPSHOT.jar /app/petLover.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/petLover.jar"]