# docker-automation

This repo contains a simple docker container that runs some selenium tests in FF and Chrome using Selenium Grid.

## Getting Setup
1. Install [docker][0]
2. Install [docker-compose][2]

## Initialize and manage
```bash
# Build the container
docker-compose build
# Run the container.  This will execute the mvn task.
docker-compose up -d
# Login in the container
docker-compose exec docker-automation /bin/bash (MAC)
docker-compose exec docker-automation bash /bin/bash (For Windows)
```

[0]: https://www.docker.com/products/overview "Docker"
[2]: https://docs.docker.com/compose/install/ "Docker Compose"

## Run Test 
Execute the following command to run the tests in Chrome
mvn test -Dbrowser=chrome-headless -Dtag=docker-automation -DsuiteXmlFile=test.xml -Durl=http://google.com

To execute the test in FF
mvn test -Dbrowser=ff-headless -Dtag=docker-automation -DsuiteXmlFile=test.xml -Durl=http://google.com



