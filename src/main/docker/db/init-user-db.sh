#!/bin/bash
set -e

echo " docker init-user:postgres , story user: $POSTGRES_STORY_USER"
psql -v ON_ERROR_STOP=1 --username "postgres" <<-EOSQL
    CREATE USER "$POSTGRES_STORY_USER" LOGIN NOSUPERUSER NOCREATEDB ;
    ALTER USER "$POSTGRES_STORY_USER" WITH PASSWORD '$POSTGRES_STORY_PASSWORD';
    CREATE DATABASE $POSTGRES_STORY_DB;
    GRANT ALL PRIVILEGES ON DATABASE $POSTGRES_STORY_DB TO $POSTGRES_STORY_USER;
EOSQL