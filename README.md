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

