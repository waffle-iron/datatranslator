#!/usr/bin/env bash

API_SERVER_HOST=localhost
API_SERVER_PORT=8080
POSTGRES_BACKEND_IP=$(docker inspect --format=" {{ .NetworkSettings.Networks.bridge.IPAddress }} " backend | tr -d ' ')

if [[ -z $POSTGRES_BACKEND_IP ]]; then
    echo "ERROR: backend database is not running..."
    exit 1
fi

if [[ -n $(docker ps -a | grep api-server) ]]; then
    docker stop api-server
    docker rm -fv api-server
    if [[ -n "$1" && ( ${1} = "--build" || ${1} = "-b" ) ]]; then
        docker rmi -f api-server
    fi
fi
if [[ -z $(docker images |  grep api-server) ]]; then
    docker build -t api-server .
fi

docker run --name api-server -d \
    -e API_SERVER_HOST=${API_SERVER_HOST} \
    -e API_SERVER_PORT=${API_SERVER_PORT} \
    -e POSTGRES_BACKEND_IP=${POSTGRES_BACKEND_IP} \
    -p ${API_SERVER_PORT}:8080 \
    api-server

exit 0;