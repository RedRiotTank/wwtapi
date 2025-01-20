FROM ghcr.io/redriottank/wwtapi:db

ENV MYSQL_ROOT_PASSWORD=root_password
ENV MYSQL_DATABASE=wwtdb
ENV MYSQL_USER=wwt
ENV MYSQL_PASSWORD=wwt_password

COPY init.sql /docker-entrypoint-initdb.d/

ENV PORT=3306
EXPOSE 3306

