## Swagger Editor

Project [swagger-editor](https://github.com/swagger-api/swagger-editor) at Github.

---

## How to use

A script named **swagger** is included in this directory and makes reference to the local **docker-compose.yml** file for configuration.

### swagger

For simplicity, a control script has been created that implements the basic functionality for the included swagger editor implemented in docker.

```
$ ./swagger help
--- Swagger Usage ---
usage: ./swagger help                        # Display this usage message
usage: ./swagger editor start                # Starts swagger-editor container
usage: ./swagger editor stop                 # Stops swagger-editor container
```

The **swagger** script uses a **docker-compose.yml** file. By default it will specify that the swagger-editor container expose port **8080** of the host it is being run on.

```yaml
swagger-editor:
  image: swaggerapi/swagger-editor
  container_name: swagger-editor
  ports:
  - "8080:8080"
```

### start, stop

The **editor start** command will build and deploy the swagger-editor container

```
$ ./swagger editor start
Creating swagger-editor
$ docker-compose ps
     Name                   Command               State           Ports
--------------------------------------------------------------------------------
swagger-editor   http-server --cors -p8080  ...   Up      0.0.0.0:8080->8080/tcp
```

At this point the swagger-editor is running on port 8080.
Similarly the swagger-editor container can be stopped with an **editor stop** command.

```
$ ./swagger editor stop
Stopping swagger-editor ... done
```