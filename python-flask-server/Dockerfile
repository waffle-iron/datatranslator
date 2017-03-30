FROM python:3
MAINTAINER Michael J. Stealey <michael.j.stealey@gmail.com>

COPY . /datatranslator
WORKDIR /datatranslator

RUN pip install --upgrade pip \
    && pip install -r requirements.txt

ENTRYPOINT ["/datatranslator/docker-entrypoint.sh"]
EXPOSE 8080
CMD ["api"]