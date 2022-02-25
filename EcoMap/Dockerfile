FROM tomcat:10-jre11
EXPOSE 8080
RUN rm -fr /usr/local/tomcat/webapps/ROOT
COPY ./EcoMap/target/EcoMap.war /usr/local/tomcat/webapps/ROOT.war
COPY ./EcoMap/src/main/webapp/WEB-INF/lib/mysql-connector-java-8.0.28.jar /usr/local/tomcat/lib
CMD ["catalina.sh", "run"]