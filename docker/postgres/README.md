# FindMe Postgres container
The container is built ontop of the default postgres container. The only differences are that the script(s) in docker-entrypoint-initdb.d/ have been added to the image. These will run when the container is run.   

start_db.sh wraps docker run and expects the following environment variables to be set:
1. PG_APP_USER
2. PG_APP_PASS

The values set are used by the application to connect to the database

