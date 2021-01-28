#!/bin/bash

EC2_INSTANCE="$1"

# Copy new yml file
sudo scp -i ~/Security/My-EC2-instance-key-pair.pem ./docker-compose.yml ec2-user@$EC2_INSTANCE:~

# Start services
ssh -i "~/Security/My-EC2-instance-key-pair.pem" ec2-user@$EC2_INSTANCE <<'ENDSSH2'
sudo service docker start
docker-compose pull
docker-compose up -d
ENDSSH2
