FROM frolvlad/alpine-oraclejdk8:slim
ADD pinpoint-agent-1.7.3.tar.gz /assets/pinpoint-agent/
ADD vloum-agent.sh /usr/local/bin/
ADD configure-agent.sh /usr/local/bin/
ADD cmd-app.sh /assets/pinpoint-agent/

RUN apk add --update bash
RUN cp -v /assets/pinpoint-agent/pinpoint.config /assets/pinpoint.config
RUN chmod a+x /usr/local/bin/vloum-agent.sh
RUN chmod a+x /usr/local/bin/configure-agent.sh
RUN chmod a+x /assets/pinpoint-agent/cmd-app.sh
RUN rm /var/cache/apk/*

ENTRYPOINT ["/usr/local/bin/configure-agent.sh"]

