#!/bin/bash

CONTAINER_NAME=tomcat-container
LOG_FILE="localhost.$(date +%Y-%m-%d).log"

docker exec -t "$CONTAINER_NAME" bash -c "tail -n 100 /usr/local/tomcat/logs/$LOG_FILE"
