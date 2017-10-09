#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
  CREATE USER findme_app;
  CREATE DATABASE findme;
  GRANT ALL PRIVILEGES ON DATABASE findme TO findme_app;
  ALTER USER findme_app with PASSWORD '$POSTGRES_APP_PASSWORD';
EOSQL

