#!/bin/bash

docker run -p 8080:8080 \
-v /config/findme:/config \
thekingwizard/tomcat:version0.3
