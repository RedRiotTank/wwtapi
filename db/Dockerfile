FROM ghcr.io/redriottank/wwtapi:db

# Configura las variables de entorno
ENV MYSQL_ROOT_PASSWORD=root_password
ENV MYSQL_DATABASE=wwtdb
ENV MYSQL_USER=wwt
ENV MYSQL_PASSWORD=wwt_password
ENV PORT=3306

# Copia el script SQL
COPY init.sql /docker-entrypoint-initdb.d/

# Copia el archivo de configuración personalizado
COPY my.cnf /etc/mysql/my.cnf

# Exponer el puerto predeterminado (opcional para documentación)
EXPOSE 3306

# Inicia MariaDB
CMD ["mysqld"]
