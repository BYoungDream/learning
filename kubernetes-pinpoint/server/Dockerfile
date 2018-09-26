FROM java:8-jdk
MAINTAINER gyf

ENV HBASE_VERSION=1.2.6
ENV BASE_DIR=/opt/hbase
ENV HBASE_HOME=${BASE_DIR}/hbase-${HBASE_VERSION}

#pinpoint-collector
COPY pinpoint-collector-1.7.3.war /opt/
COPY start-collector.sh /usr/local/bin/

#pinpoint-web
COPY pinpoint-web-1.7.3.war /opt/
COPY start-web.sh /usr/local/bin/

#pinpoint-hbase
ADD hbase-1.2.6-bin.tar.gz /opt/hbase/
COPY hbase-create.hbase /opt/hbase/
COPY hbase-site.xml /opt/hbase/

#tomcat
ADD apache-tomcat-8.5.32.tar.gz /usr/local/collector/
ADD apache-tomcat-8.5.32.tar.gz /usr/local/web/

#start all application
COPY cmd-app.sh /usr/local/bin

RUN cd /opt/hbase \
    && mv hbase-site.xml /opt/hbase/hbase-1.2.6/conf/hbase-site.xml \
    && /opt/hbase/hbase-1.2.6/bin/start-hbase.sh \
    && sleep 10 \
    && /opt/hbase/hbase-1.2.6/bin/hbase shell /opt/hbase/hbase-create.hbase \
    && /opt/hbase/hbase-1.2.6/bin/stop-hbase.sh \
    && rm /opt/hbase/hbase-create.hbase \
    && chmod a+x /usr/local/bin/start-collector.sh \
	&& rm -rf /usr/local/collector/apache-tomcat-8.5.32/webapps \
	&& mkdir -p /usr/local/collector/apache-tomcat-8.5.32/webapps \
    && unzip /opt/pinpoint-collector-1.7.3.war -d /usr/local/collector/apache-tomcat-8.5.32/webapps/ROOT \
    && rm -rf /opt/pinpoint-collector-1.7.3.war \
    && sed -i "s/8005/9005/g" /usr/local/collector/apache-tomcat-8.5.32/conf/server.xml \
    && sed -i "s/8080/9080/g" /usr/local/collector/apache-tomcat-8.5.32/conf/server.xml \
    && sed -i "s/8009/9009/g" /usr/local/collector/apache-tomcat-8.5.32/conf/server.xml \
    && sed -i "s/8443/9443/g" /usr/local/collector/apache-tomcat-8.5.32/conf/server.xml \
    && chmod a+x /usr/local/bin/start-web.sh \
	&& rm -rf /usr/local/web/apache-tomcat-8.5.32/webapps \
	&& mkdir -p /usr/local/web/apache-tomcat-8.5.32/webapps \
    && unzip /opt/pinpoint-web-1.7.3.war -d /usr/local/web/apache-tomcat-8.5.32/webapps/ROOT \
    && rm -rf /opt/pinpoint-web-1.7.3.war \
    && chmod a+x /usr/local/bin/cmd-app.sh

	
ENTRYPOINT ["/bin/bash","/usr/local/bin/cmd-app.sh"]
