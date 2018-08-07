#!/bin/bash
mvn test -DsuiteXmlFile=smokeTesting.xml & java -jar /usr/local/bin/selenium-server-standalone-3.9.0.jar -role hub -hubConfig src/com/bluemoon/resources/grid/GridHub.json & java -Dwebdriver.chrome.driver=/usr/local/bin/chromedriver -jar /usr/local/bin/selenium-server-standalone-3.9.0.jar -port 5556 -role node -nodeConfig src/com/bluemoon/resources/grid/GridNode.json
