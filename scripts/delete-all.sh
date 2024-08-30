#!/usr/bin/env bash

aws s3 rm s3://aleksandr-lambda-dev-build/url-shortener-1.0.jar

aws cloudformation delete-stack \
    --region us-east-1 \
    --stack-name url-shortener