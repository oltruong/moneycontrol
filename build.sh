#!/bin/sh

(cd front && npm run build)

rm -rf back/src/main/resources/public/*
cp -R front/dist/ back/src/main/resources/META-INF/resources/

(cd back && ./gradlew quarkusBuild --uber-jar --scan)