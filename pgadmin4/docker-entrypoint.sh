#!/usr/bin/env bash

if [[ "$1" = 'pgadmin' ]]; then
    useradd -d /var/pgadmin --system -s /usr/sbin/nologin -c "pgAdmin System Account" -U pgadmin
    usermod -u ${PGADMIN_UID} pgadmin
    groupmod -g ${PGADMIN_GID} pgadmin
    chown -R ${PGADMIN_UID}:${PGADMIN_GID} /pgadmin4 /var/pgadmin
    su --shell /bin/bash -c "python /pgadmin4/web/pgAdmin4.py" pgadmin
else
    exec "$@"
fi