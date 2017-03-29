#!/usr/bin/env bash

if [[ "$1" = 'api' ]]; then
    # update connexion.ini file
    cp ini/connexion-template.ini ini/connexion.ini

    # update swagger.yaml file
    sed -i 's/host.*/host: \"'${API_SERVER_HOST}':'${API_SERVER_PORT}'\"/g' /datatranslator/swagger/swagger.yaml

    # update /etc/hosts if BACKEND_IP is passed in
    if [[ -n $POSTGRES_BACKEND_IP ]]; then
        echo "${POSTGRES_BACKEND_IP} backend" >> /etc/hosts
    fi

    # run the app
    python app.py
else
    exec "$@"
fi