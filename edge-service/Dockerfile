FROM java:8-jre
MAINTAINER Chris Turner <peavers@gmail.com>

ADD ./build/libs/edge-service-1.0.jar /app/
ADD docker-startup.sh /tmp/docker-startup.sh

CMD ["/bin/bash", "/tmp/docker-startup.sh"]

EXPOSE 8443