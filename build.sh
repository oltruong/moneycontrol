#!/bin/sh

(cd front && npm run build)

rm -rf back/src/main/resources/public/*
cp -r front/dist/* back/src/main/resources/public/

(cd back && ./gradlew build --scan)