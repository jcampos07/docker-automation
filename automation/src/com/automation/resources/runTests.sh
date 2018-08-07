#!/bin/bash
mvn test -Dbrowser=chrome-headless -Dtag=docker-automation -DsuiteXmlFile=test.xml -DexecutionMode=local -Durl=http://google.com
