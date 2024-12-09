FROM tomcat:10.1-jdk21-openjdk-slim

COPY target/api-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

COPY wait-for-it.sh /usr/local/bin/wait-for-it.sh

RUN chmod +x /usr/local/bin/wait-for-it.sh

EXPOSE 8080

CMD ["/usr/local/bin/wait-for-it.sh", "elasticsearch:9200", "--", "catalina.sh", "run"]