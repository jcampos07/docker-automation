#!/bin/bash

#Necessary to run the chrome and ff test, we need to share the memory, if not, chrome crash problem is gotten
umount /dev/shm
mount -t tmpfs -o rw,nosuid,nodev,noexec,relatime,size=512M tmpfs /dev/shm


cd /opt/automation
rm -rf reports/*
firefox --version

java -jar /usr/local/bin/selenium-server-standalone-3.9.0.jar -role hub -hubConfig /usr/local/bin/grid/GridHub.json & 
java -Dselenium.LOGGER.level=WARNING -jar /usr/local/bin/selenium-server-standalone-3.9.0.jar -port 5556 -role node -nodeConfig /usr/local/bin/grid/GridNode.json & 
java -Dselenium.LOGGER.level=WARNING -jar /usr/local/bin/selenium-server-standalone-3.9.0.jar -port 5557 -role node -nodeConfig /usr/local/bin/grid/GridNode.json & 
mvn test -DsuiteXmlFile=$TESTSUITE -Durl=$TARGETURL -Dbrowser=$BROWSER -Dtag=$TAG -DexecutionMode=$EXECUTIONMODE