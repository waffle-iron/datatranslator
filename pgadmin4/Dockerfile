FROM centos:7
MAINTAINER Michael J. Stealey <michael.j.stealey@gmail.com>

RUN rpm -ivh http://yum.postgresql.org/9.5/redhat/rhel-7-x86_64/pgdg-centos95-9.5-2.noarch.rpm \
    && yum install -y \
       epel-release \
       postgresql95 \
       git \
       python-devel \
       libpqxx-devel \
       gcc

RUN git clone https://git.postgresql.org/git/pgadmin4.git \
    && yum install -y python-pip \
    && pip install --upgrade pip \
    && PATH=$PATH:/usr/pgsql-9.5/bin pip install -r /pgadmin4/requirements.txt

WORKDIR /
COPY . /tmp
RUN cp /tmp/docker-entrypoint.sh /docker-entrypoint.sh \
    && cp /tmp/config_local.py /pgadmin4/web/config_local.py \
    && rm -rf /tmp/* \
    && yum remove -y \
       epel-release \
       postgresql95 \
       git \
       python-devel \
       libpqxx-devel \
       gcc \
    && yum clean all

ENTRYPOINT ["/docker-entrypoint.sh"]
EXPOSE 5050

CMD ["pgadmin"]