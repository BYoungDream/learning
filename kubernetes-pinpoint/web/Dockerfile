FROM 192.168.0.177/library/docker.io/tomcat:SH8-jre8

MAINTAINER gyf
LABEL version = "v1.0.0"
LABEL description = "this is pinpoint-web docker"

COPY pinpoint-web-1.7.3.war /opt/
COPY start-web.sh /usr/local/bin/

RUN chmod a+x /usr/local/bin/start-web.sh \
    && rm -rf /usr/local/tomcat/webapps \
    && mkdir -p /usr/local/tomcat/webapps \
    && unzip /opt/pinpoint-web-1.7.3.war -d /usr/local/tomcat/webapps/ROOT \
    && rm -rf /opt/pinpoint-web-1.7.3.war

ENTRYPOINT ["/usr/local/bin/start-web.sh"]
