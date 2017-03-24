# datatranslator
Local development for datatranslator

- **backend**: PostgreSQL 9.5 with PostGIS 2.2.5
- **pgadmin**: pgAdmin 4
- **swagger-editor**: swagger-editor
- **python-flask-server**: connexion w/ gevent server
    - `datatranslator/python-flask-server/requirements.txt` in develop branch
    - [![Requirements Status](https://requires.io/github/mjstealey/datatranslator/requirements.svg?branch=develop)](https://requires.io/github/mjstealey/datatranslator/requirements/?branch=develop) 

---

Assumes use of docker and docker-compose within a Linux environment. A VirtualBox .ova example file can be found here: [docker-lubuntu-16.10.ova](http://distribution.hydroshare.org/public_html/docker-lubuntu-16.10.ova)

- VirtualBox:**File** > **Import Appliance**
- Select **docker-lubuntu-16.10.ova**
- Check: **Reinitialize the MAC address of all network cards**
- Import
- Network settings, Shared Folders and Guest Additions updates are the responsibility of the user and will not be outlined here
- Default user with sudo rights is:
  - username: **docker**
  - password: **docker**

---

## How to use

It is recommended to run `docker-compose build` prior to issuing any **dbctl** commands. This will ensure that an up to date build image is being used.

### dbctl

For simplicity, a control script has been created that implements the basic functionality for the PostgreSQL / Postgis backend.

```
$ ./dbctl help
--- Backend PostgreSQL Usage ---
usage: ./dbctl help                        # Display this usage message
usage: ./dbctl start                       # Starts backend database container
usage: ./dbctl stop                        # Stops backend database container
usage: ./dbctl restart                     # restarts backend database container
usage: ./dbctl build                       # issues docker-compose build call
usage: ./dbctl psql                        # Connects to backend database as postgres user
usage: ./dbctl psql USER PASS DATABASE     # Connects to backend DATABASE as USER:PASS
usage: ./dbctl purge                       # Remove backend database and container
usage: ./dbctl pgadmin start               # Start the pgadmin container
usage: ./dbctl pgadmin stop                # Stop the pgadmin container
```

The `dbctl` script uses a **docker-compose.yml** file with the following default configuration. The PostgreSQL port will be opened on **5432** of the host it is being run on.

```yaml
backend:
  build: backend
  container_name: backend
  volumes:
  - "/var/lib/pgsql"
  ports:
  - "5432:5432"
  command: run
pgadmin:
  build: pgadmin4
  container_name: pgadmin
  environment:
    PGADMIN_UID: 1000
    PGADMIN_GID: 1000
  volumes:
    - "/var/pgadmin"
  ports:
  - "5050:5050"
  command: pgadmin
```

### start, stop, restart

The **start** command will build and deploy the backend container

```
$ ./dbctl start
Creating backend
$ docker-compose ps
 Name              Command            State           Ports
--------------------------------------------------------------------
backend   /docker-entrypoint.sh run   Up      0.0.0.0:5432->5432/tcp
```

At this point the PostgreSQL database is running and has a single administrative user named **postgres**.

Similarly the backend container can be stopped or restarted.

```
$ ./dbctl stop
Stopping backend ...
```

```
$ ./dbctl restart
Stopping backend ... done
Starting backend ... done
```

Start, stop, and restart operations will not destroy the container or it's contents and data should not be lost when executing these actions.

### psql

The **psql** command can be used to connect to the running backend container as the **postgres** user with the **psql** prompt.

```
$ ./dbctl psql
psql (9.5.6)
Type "help" for help.

postgres=#
```

The user can interact with the database from here in the normal way.

```
postgres=# \l
                                  List of databases
   Name    |  Owner   | Encoding |   Collate   |    Ctype    |   Access privileges
-----------+----------+----------+-------------+-------------+-----------------------
 postgres  | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 |
 template0 | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =c/postgres          +
           |          |          |             |             | postgres=CTc/postgres
 template1 | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =c/postgres          +
           |          |          |             |             | postgres=CTc/postgres
(3 rows)

postgres=#
```

Example: Create user: **myuser** with password: **mypassword** and grant all access to database: **mydatabase**

```
postgres=# CREATE USER myuser WITH PASSWORD 'mypassword';
CREATE ROLE
postgres=# CREATE DATABASE mydatabase;
CREATE DATABASE
postgres=# GRANT ALL PRIVILEGES ON DATABASE "mydatabase" TO myuser;
GRANT
postgres-# \l
                                  List of databases
    Name    |  Owner   | Encoding |   Collate   |    Ctype    |   Access privileges
------------+----------+----------+-------------+-------------+-----------------------
 mydatabase | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =Tc/postgres         +
            |          |          |             |             | postgres=CTc/postgres+
            |          |          |             |             | myuser=CTc/postgres
 postgres   | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 |
 template0  | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =c/postgres          +
            |          |          |             |             | postgres=CTc/postgres
 template1  | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =c/postgres          +
            |          |          |             |             | postgres=CTc/postgres
(4 rows)

postgres-#
```

### psql USER PASS DATABASE

The user can connect to a particular database as a particular user with the syntax of `./dbctl psql USER PASS DATABASE`.

Example, using the credentials from the **myuser** created above.

```
$ ./dbctl psql myuser mypassword mydatabase
psql (9.5.6)
Type "help" for help.

mydatabase=>
```

### build

The build call will rebuild the backend container according the definitions within the Dockerfile. 
This action could very well destroy any data that was previously entered into the database if a new container image is generated. If no new image is generated, then the previous data should persist as shown below.

```
$ ./dbctl build
Stopping backend ... done
Building backend
Step 1/14 : FROM centos:7
 ---> 67591570dd29
...
Step 14/14 : CMD run
 ---> Running in e9aa85cffc83
 ---> 7f09c2988c68
Removing intermediate container e9aa85cffc83
Successfully built 7f09c2988c68
$ ./dbctl start
Starting backend ... done
$ ./dbctl psql myuser mypassword mydatabase
psql (9.5.6)
Type "help" for help.

mydatabase=>
``` 

### purge

The purge command is meant to destroy the database container and all of it's contents. Data will not be persisted if this option is used.

Example:

```
$ ./dbctl psql myuser mypassword mydatabase
psql (9.5.6)
Type "help" for help.

mydatabase=> \q
$ ./dbctl purge
Stopping backend ... done
Going to remove backend
Removing backend ... done
$ ./dbctl start
Creating backend
$ ./dbctl psql myuser mypassword mydatabase
psql: FATAL:  password authentication failed for user "myuser"
password retrieved from file "/root/.pgpass"
```

### pgadmin start

Starts a container named **pgadmin** running pgAdmin4 on port 5050. If the container has not already been built, a `docker-compose build` call will be issued prior to starting the container.

```
$ ./dbctl pgadmin start
Starting pgadmin
```

The port being exposed can be changed by modifying the value in the **docker-compose.yml** file. For example, if you'd want to expose **pgadmin** on port 8080, the file woudl be updated as such.

```yaml
pgadmin:
  build: pgadmin4
  container_name: pgadmin
  environment:
    PGADMIN_UID: 1000
    PGADMIN_GID: 1000
  volumes:
    - "/var/pgadmin"
  ports:
  - "8080:5050" # <-- Expose port 8080 of host
  command: pgadmin
```

If the **pgadmin** container was already running when the change to the port was made, it would need to be stopped and started again for the changes to take effect.

### pgadmin stop

Stops the container named **pgadmin** running pgAdmin4 on port 5050.

```
$ ./dbctl pgadmin stop
Stopping pgadmin ... done
```

## Persisting data between builds / purges

It is possible to share a directory from the host with the backend container to store the database contents. This is achieved by defining a volume in the **docker-compose.yml** file to mount as `/var/lib/pgsql` in container space.

```yaml
backend:
  build: backend
  container_name: backend
  volumes:
  - "/home/${USER}/pgdata:/var/lib/pgsql"
  ports:
  - "5432:5432"
  command: run
pgadmin:
  build: pgadmin4
  container_name: pgadmin
  environment:
    PGADMIN_UID: 1000
    PGADMIN_GID: 1000
  volumes:
    - "/home/${USER}/pgadmin:/var/pgadmin"
  ports:
  - "5050:5050"
  command: pgadmin
```

Once the backend is started a directory will be created at the specified `/home/${USER}/pgdata` location. The ownership of the files may not coincide to any users on the host as they are relevant to the users defined within the container, and as such may only be viewable using the `sudo` command.

```
$ ./dbctl start
Creating backend
$ sudo ls -alh /home/$USER/pgdata/9.5/
total 16K
drwxr-xr-x  3 root root 4.0K Mar 10 12:21 .
drwxr-xr-x  3 root root 4.0K Mar 10 12:21 ..
drwx------ 20   26 tape 4.0K Mar 10 12:21 data
-rw-------  1   26 tape 1.3K Mar 10 12:21 initdb.log
```

Once the pgadmin container is started a directory will be created at the specified `/home/${USER}/pgadmin` location. The ownership of the files may not coincide to any users on the host as they are relevant to the users defined within the container by the **PGADMIN_UID** and **PGADMIN_GID** environment variables, and as such may only be viewable using the `sudo` command.

```
$ ./dbctl pgadmin start
Creating pgadmin
$ sudo ls -alh /home/$USER/pgadmin/.pgadmin
total 5.7M
drwx------ 3 docker docker 4.0K Mar 16 21:03 .
drwxr-xr-x 3 docker docker 4.0K Mar 16 21:02 ..
-rw------- 1 docker docker  34K Mar 16 21:03 pgadmin4-desktop.db
-rw-r--r-- 1 docker docker 5.6M Mar 16 21:31 pgadmin4.log
drwxr-xr-x 2 docker docker 4.0K Mar 16 21:02 sessions
```

These directories will remain in place with whatever data has been populated to them until they are either removed from the host, or the volume definition is changed in the **docker-compose.yml** file.
