#!/bin/bash

echo "running container..."
docker run -it -p 9000:9000 --rm beverly-api

exit 0