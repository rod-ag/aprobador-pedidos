#!/bin/bash

EC2_INSTANCE="$1"

# Stop existing containers
ssh -i "~/Security/My-EC2-instance-key-pair.pem" ec2-user@$EC2_INSTANCE <<'ENDSSH1'
docker-compose down -v
sudo service docker stop
ENDSSH1

