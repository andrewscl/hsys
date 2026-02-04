# Etapa 1: Compilación del código con Maven
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copia el archivo de configuración Maven y descargas las dependencias necesarias
COPY pom.xml .
COPY .mvn/ .mvn
COPY mvnw .

# Rseuelve dependencias para acelerar la construcción
RUN ./mvnw dependency:go-offline -B

# Copia el código fuente completo y genera el JAR
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagen final para ejecución del proyecto
FROM eclipse-temurin:21-jre

WORKDIR /app

# copia el JAR compilado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto donde se ejecutara el servicio
EXPOSE 8081

# Define el punto de entrada para el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]