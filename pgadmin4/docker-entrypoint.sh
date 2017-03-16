#!/usr/bin/env bash

if [[ "$1" = 'pgadmin' ]]; then
    exec python /pgadmin4/web/pgAdmin4.py
else
    exec "$@"
fi