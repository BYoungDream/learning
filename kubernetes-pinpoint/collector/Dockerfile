FROM 192.168.0.177/library/docker.io/tomcat:SH8-jre8
MAINTAINER gyf
LABEL version = "v1.0.0"
LABEL description = "this is pinpoint-collector docker"

COPY pinpoint-collector-1.7.3.war /opt/

COPY start-collector.sh /usr/local/bin/

RUN chmod a+x /usr/local/bin/start-collector.sh \
    && rm -rf /usr/local/tomcat/webapps \
    && mkdir -p /usr/local/tomcat/webapps \
    && unzip /opt/pinpoint-collector-1.7.3.war -d /usr/local/tomcat/webapps/ROOT \
    && rm -rf /opt/pinpoint-collector-1.7.3.war \
    && sed -i "s/8005/9005/g" /usr/local/tomcat/conf/server.xml \
    && sed -i "s/8080/9080/g" /usr/local/tomcat/conf/server.xml \
    && sed -i "s/8009/9009/g" /usr/local/tomcat/conf/server.xml \
    && sed -i "s/8443/9443/g" /usr/local/tomcat/conf/server.xml

ENTRYPOINT ["/usr/local/bin/start-collector.sh"]
