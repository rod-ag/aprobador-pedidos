#!/bin/bash

# configure profile
ecs-cli configure profile --profile-name rag_profile --access-key AKIAJBAX7IAWWFNKJ2SQ --secret-key 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

# configure cluster
ecs-cli configure --cluster pedidosCluster --default-launch-type EC2 --region eu-west-1 --config-name my_cluster_config

# setup cloud formation template
ecs-cli up --keypair My-EC2-instance-key-pair --capability-iam --force --security-group sg-20faaa6b --vpc vpc-f854b281 --subnets subnet-01597b49,subnet-4bb5e62d,subnet-29fb6773 --instance-type t2.xlarge --ecs-profile rag_profile --cluster-config my_cluster_config

# wait for EC2 instance
echo "Waiting for EC2 instance and containers ....."
sleep 15

# deploy and run
ecs-cli compose --cluster-config my_cluster_config --file aws-compose.yml up

# check
ecs-cli ps --cluster-config my_cluster_config

