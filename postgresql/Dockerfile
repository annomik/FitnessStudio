FROM postgres:15.2-alpine
MAINTAINER HannaM

ADD /ddl/*.sql /docker-entrypoint-initdb.d/
ADD /dml/*.sql /docker-entrypoint-initdb.d/
