FROM tomcat:8.5.59-jdk8-openjdk-slim 
MAINTAINER wangmengqiang001@126.com

# copy the trusted certification
RUN echo "copy the trusted certification"
COPY ./isc_x509.crt /usr/local/isc_x509.crt
RUN keytool -printcert -file /usr/local/isc_x509.crt  -v
# import the certification to keystore
RUN keytool -importcert -noprompt -trustcacerts -keystore "$JAVA_HOME/jre/lib/security/cacerts" -storepass "changeit" -file "/usr/local/isc_x509.crt" -alias iscsso



#verify the import success
RUN echo "list iscsso "
RUN keytool -list -keystore "$JAVA_HOME/jre/lib/security/cacerts" -storepass "changeit" -alias iscsso

# remove the certification
RUN rm -f /usr/local/isc_x509.crt
