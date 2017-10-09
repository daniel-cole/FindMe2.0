#!/bin/bash

docker run -p 5432:5432 \
-e POSTGRES_USER=findme_admin \
-e POSTGRES_PASSWORD=$PG_ADMIN_PASS \
-e POSTGRES_APP_PASSWORD=$PG_APP_PASS \
-d thekingwizard/postgres:version0.1 
