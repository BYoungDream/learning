# DESCRIPTION       Pinpoint APM HBase (Data Storage)
# TO_BUILD          docker build -t pinpoint-hbase .
# TO_RUN            docker run -d --net=host -p 2181:2181 -p 60000:60000 -p 16010:16010 -p 60020:60020 -p 16030:16030 --name=pinpoint-hbase pinpoint-hbase

FROM java:8-jdk

ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV HBASE_VERSION=1.2.6
ENV HBASE_HOME=/opt/hbase/hbase-$HBASE_VERSION

ADD hbase-1.2.6-bin.tar.gz /opt/hbase/
ADD hbase-site.xml /opt/hbase/hbase-$HBASE_VERSION/conf/hbase-site.xml
ADD hbase-create.hbase /opt/hbase/

ADD hbase-startup.sh /usr/local/bin/

#RUN $HBASE_HOME/bin/start-hbase.sh \
#    && sleep 10 \
#    && $HBASE_HOME/bin/hbase shell /opt/hbase/hbase-create.hbase \
#    && $HBASE_HOME/bin/stop-hbase.sh

RUN chmod a+x /usr/local/bin/hbase-startup.sh

VOLUME ["/home/pinpoint/hbase", "/home/pinpoint/zookeeper"]

# zookeeper
EXPOSE 2181
# HBase Master API port
EXPOSE 60000
# HBase Master Web UI
EXPOSE 16010
# Regionserver API port
EXPOSE 60020
# HBase Regionserver web UI
EXPOSE 16030

#ENTRYPOINT /opt/hbase/hbase-$HBASE_VERSION/bin/hbase master start

ENTRYPOINT /usr/local/bin/hbase-startup.sh
