#!/usr/bin/env bash

aws cloudformation deploy \
    --stack-name url-shortener \
    --template-file template.yml \
    --region us-east-1 \
    --capabilities CAPABILITY_IAM \
    --force-upload