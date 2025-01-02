FROM mariadb:11.4.3

ENV MYSQL_ROOT_PASSWORD=root_password
ENV MYSQL_DATABASE=wwtdb
ENV MYSQL_USER=wwt
ENV MYSQL_PASSWORD=wwt_password

COPY init.sql /docker-entrypoint-initdb.d/

EXPOSE 3306
