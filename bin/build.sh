#!/bin/bash

# step 1 build
sbt clen update gssdist

# step 2 copy and clean
cp target/universal/beverly-api-1.0-SNAPSHOT.zip $BEVERLY_INFRA/app