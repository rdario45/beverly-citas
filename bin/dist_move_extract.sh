#!/bin/bash

echo "creating distro zip"
sbt dist

echo "moving zip file"
mv target/universal/beverly-api-1.0-SNAPSHOT.zip bin/

echo "unzip in app folder"
unzip -d bin/app bin/beverly-api-1.0-SNAPSHOT.zip

exit 0