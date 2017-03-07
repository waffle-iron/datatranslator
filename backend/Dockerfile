FROM centos:7
MAINTAINER Michael J. Stealey <michael.j.stealey@gmail.com>

ENV LANGUAGE="en_US.UTF-8"
ENV LANG="en_US.UTF-8"
ENV LC_ALL="en_US.UTF-8"

ENV GOSU_VERSION 1.10
RUN set -x \
    && yum -y install epel-release \
    && yum -y install wget dpkg \
    && dpkgArch="$(dpkg --print-architecture | awk -F- '{ print $NF }')" \
    && wget -O /usr/bin/gosu "https://github.com/tianon/gosu/releases/download/$GOSU_VERSION/gosu-$dpkgArch" \
    && wget -O /tmp/gosu.asc "https://github.com/tianon/gosu/releases/download/$GOSU_VERSION/gosu-$dpkgArch.asc" \
    && export GNUPGHOME="$(mktemp -d)" \
    && gpg --keyserver ha.pool.sks-keyservers.net --recv-keys B42F6819007F00F88E364FD4036A9C25BF357DD4 \
    && gpg --batch --verify /tmp/gosu.asc /usr/bin/gosu \
    && rm -r "$GNUPGHOME" /tmp/gosu.asc \
    && chmod +x /usr/bin/gosu \
    && gosu nobody true \
    && yum -y remove wget dpkg

RUN rpm -ivh http://yum.postgresql.org/9.5/redhat/rhel-7-x86_64/pgdg-centos95-9.5-2.noarch.rpm \
    && yum install -y \
    sudo \
    postgresql95 \
    postgresql95-server \
    postgresql95-libs \
    postgresql95-contrib \
    postgresql95-devel \
    postgis2_95 \
    postgis2_95-client \
    ogr_fdw95

# Shenanigans to make dockerhub happy
COPY . /tmp
RUN cp /tmp/docker-entrypoint.sh /docker-entrypoint.sh \
    && cp /tmp/.pgsql_profile /var/lib/pgsql/.pgsql_profile \
    && chown postgres:postgres /var/lib/pgsql/.pgsql_profile \
    && rm -rf /tmp/* \
    && yum clean all

VOLUME ["/var/lib/pgsql"]

EXPOSE 5432
ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["run"]