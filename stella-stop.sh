#!/bin/bash
docker-compose -f ./bos-plugin/docker-compose.yml down
helm uninstall stella-deploy
helm uninstall livy-deploy
