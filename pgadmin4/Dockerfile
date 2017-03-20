FROM centos:7
MAINTAINER Michael J. Stealey <michael.j.stealey@gmail.com>

# Install PostgreSQL 9.5 base and other requirements for pgAdmin 4
RUN rpm -ivh http://yum.postgresql.org/9.5/redhat/rhel-7-x86_64/pgdg-centos95-9.5-2.noarch.rpm \
    && yum install -y \
       epel-release \
       postgresql95 \
       git \
       python-devel \
       libpqxx-devel \
       gcc

# Git clone release 1.3 of pgAdmin 4 and build
RUN git clone https://git.postgresql.org/git/pgadmin4.git \
    && cd pgadmin4 \
    && git checkout tags/REL-1_3 -b release-1.3 \
    && yum install -y python-pip \
    && pip install --upgrade pip \
    && PATH=$PATH:/usr/pgsql-9.5/bin pip install -r /pgadmin4/requirements.txt

# Add files to image and clean up
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

# Set optional environment variables
ENV PGADMIN_UID=1000
ENV PGADMIN_GID=1000

# Set entrypoint and exposures
ENTRYPOINT ["/docker-entrypoint.sh"]
EXPOSE 5050
VOLUME /var/pgadmin
CMD ["pgadmin"]