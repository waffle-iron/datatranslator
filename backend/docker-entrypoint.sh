#!/usr/bin/env bash
set -e

__postgresql95-setup() {
    sed -i '/PGDATA=`systemctl show -p Environment "${SERVICE_NAME}.service" |/,+6 s/^/#/' /usr/pgsql-9.5/bin/postgresql95-setup
}

__pg_hba_conf() {
    sed -i 's!all             127.0.0.1/32            ident!all             127.0.0.1/32            md5!' /var/lib/pgsql/9.5/data/pg_hba.conf
    sed -i 's!all             ::1/128                 ident!all             ::1/128                 md5!' /var/lib/pgsql/9.5/data/pg_hba.conf
    echo "host  all  all 0.0.0.0/0 md5" >> /var/lib/pgsql/9.5/data/pg_hba.conf
}

__postgresql_conf() {
    sed -i "s/#listen_addresses = 'localhost'/listen_addresses = '*'/" /var/lib/pgsql/9.5/data/postgresql.conf
#    sed -i "s/#client_encoding = sql_ascii/client_encoding = UTF8/" /var/lib/pgsql/9.5/data/postgresql.conf
}

if [[ "$1" = 'run' ]]; then
    if [ "$(ls -A /var/lib/pgsql/9.5/data)" ]; then
        echo "WARNING: Data directory is not empty!"
    else
        __postgresql95-setup
        gosu root /usr/pgsql-9.5/bin/postgresql95-setup initdb
        __pg_hba_conf
        __postgresql_conf
    fi
    gosu postgres /usr/pgsql-9.5/bin/postgres -D /var/lib/pgsql/9.5/data -p 5432
fi