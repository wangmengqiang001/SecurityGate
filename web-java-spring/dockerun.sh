 docker run -it --name hellospring --add-host sso.isc.com:139.9.236.166 -p 8280:8080 -v $PWD/target/web-java-spring-1.0-SNAPSHOT.war:/usr/local/tomcat/webapps/hellospring.war tomcat:8.5-trustsso 
