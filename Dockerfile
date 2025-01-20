FROM maven:3.8.5-openjdk-17 AS build-stage

# Configura el directorio de trabajo dentro de la imagen
WORKDIR /app

# Copia los archivos del proyecto al contenedor
COPY pom.xml ./
COPY src ./src

# Construye la aplicación
RUN mvn clean package -DskipTests

# Fase final: utiliza la imagen base de Tomcat
FROM ghcr.io/redriottank/wwtapi:app

# Copia el artefacto construido al contenedor final
COPY --from=build-stage /app/target/api-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Copia el script de espera
COPY wait-for-it.sh /usr/local/bin/wait-for-it.sh
RUN chmod +x /usr/local/bin/wait-for-it.sh

# Expone el puerto necesario
EXPOSE 8080

# Comando de inicio
CMD ["/usr/local/bin/wait-for-it.sh", "elasticsearch:9200", "--", "catalina.sh", "run"]
