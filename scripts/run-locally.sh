#!/usr/bin/env bash

mvn clean install

mvn spring-boot:run -Dspring-boot.run.main-class=com.aleksandr.shortener.manual.UrlShortenerLocalStarter