# oktara-automation

This repo contains a simple docker container that runs the battery of selenium tests against a deployed bmf web app

## Getting Setup
1. Install [docker][0]
2. Install [docker-compose][2]
3. Copy app.env to .env and add the AWS credentials.
4. In .env set TESTSUITE, TAG, and TARGETURL before running the container.

## Initialize and manage
```bash
# Build the container
docker-compose build
# Run the container.  This will execute the mvn task.
docker-compose up -d
# Login in the container
docker-compose run --entrypoint bash test-runner
```

[0]: https://www.docker.com/products/overview "Docker"
[2]: https://docs.docker.com/compose/install/ "Docker Compose"

## Run Smoke Test 
1. Copy the following template and create a file in your computer. Name the file "start-task.json".

```bash
{
    "cluster": "QADeployment",
    "taskDefinition": "automated-tests",
    "overrides": {
        "containerOverrides": [
            {
                "name": "automated-tests",
                "command": [
                    ""
                ],
                "environment": [
                    {
                        "name": "TARGETURL",
                        "value": "{CONTAINER_URL}"
                    },
                    {
                        "name": "TESTSUITE",
                        "value": "smoke.xml"
                    },
                    {
                        "name": "TAG",
                        "value": "smoke"
                    },
                    {
                        "name": "EXECUTIONMODE",
                        "value": "grid"
                    },
                    {
                        "name": "BROWSER",
                        "value": "chrome-headless"
                    }
                ],
                "cpu": 0,
                "memory": 0,
                "memoryReservation": 0
            }
        ],
        "taskRoleArn": ""
    },
    "containerInstances": [
        "4ab79496-2d28-4ba5-80fc-6a3058a853dc",
        "912c47d6-a8f5-4283-8e7b-59250b901b72"
    ],
    "startedBy": "{YOUR_EMAIL}",
    "group": "automated-tests"
}
```

2. Replace the {CONTAINER_URL} and {YOUR_EMAIL} in the json file for the corresponding values.
3. Open the AWS cli in your computer(CMD), move to the folder where start-task.json is located.
4. Enter the following command 
```bash
#execute the task in AWS with the parameters added in start-task.json
aws ecs start-task --region us-east-1 --cli-input-json file://start-task.json
```
5. You will see in the console a Containers in Pending status with the list of parameter you set in the start-task.json, once the Smoke ends, an email with the results will be sent to the QA Team.