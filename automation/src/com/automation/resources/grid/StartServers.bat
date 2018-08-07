start cmd /k java -jar selenium-server-standalone-3.5.0.jar -role hub -hubConfig GridHub.json
start cmd /k  java -Dwebdriver.chrome.driver="%cd%\chromedriver.exe"  -Dwebdriver.gecko.driver="%cd%\geckodriver.exe" -Dphantomjs.binary.path="%cd%\phantomjs.exe" -jar selenium-server-standalone-3.5.0.jar -port 5556 -role node -nodeConfig GridNode.json
TIMEOUT 15
call mvn clean install test -DsuiteXmlFile=smoke.xml -f %cd%\..\..\..\..\..\