FROM tomcat:10-jre11
EXPOSE 8080
RUN rm -fr /usr/local/tomcat/webapps/ROOT
COPY ./EcoMap/target/EcoMap.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]