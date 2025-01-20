FROM ghcr.io/redriottank/wwtapi:db

# Configura las variables de entorno
ENV MYSQL_ROOT_PASSWORD=root_password
ENV MYSQL_DATABASE=wwtdb
ENV MYSQL_USER=wwt
ENV MYSQL_PASSWORD=wwt_password
ENV PORT=3306

# Copia el script SQL
COPY init.sql /docker-entrypoint-initdb.d/

# Exponer el puerto predeterminado (opcional para documentación)
EXPOSE 3306

# Configura MySQL para escuchar en el puerto $PORT
CMD ["sh", "-c", "sed -i 's/^port=.*/port=${PORT}/' /etc/mysql/my.cnf && exec mysqld"]
