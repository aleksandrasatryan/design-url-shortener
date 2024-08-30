#!/usr/bin/env bash

mvn clean install

aws s3 cp \
    target/url-shortener-1.0.jar \
    s3://aleksandr-lambda-dev-build

./scripts/deploy-stack.sh