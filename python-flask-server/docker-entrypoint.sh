#!/usr/bin/env bash

if [[ "$1" = 'api' ]]; then
    # update connexion.ini file
    cp ini/connexion-template.ini ini/connexion.ini
    # update swagger.yaml file

    # install requirements.txt

    # update /etc/hosts if BACKEND_IP is passed in
    useradd -d /var/pgadmin --system -s /usr/sbin/nologin -c "pgAdmin System Account" -U pgadmin
    usermod -u ${PGADMIN_UID} pgadmin
    groupmod -g ${PGADMIN_GID} pgadmin
    chown -R ${PGADMIN_UID}:${PGADMIN_GID} /pgadmin4 /var/pgadmin
    su --shell /bin/bash -c "python /pgadmin4/web/pgAdmin4.py" pgadmin
else
    exec "$@"
fi