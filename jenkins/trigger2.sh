#!/bin/sh
CRUMB=$(curl -s http://localhost:8080/crumbIssuer/api/json | sed -E 's/.*"crumb":"([^"]+)".*/\1/')
echo "crumb=[$CRUMB]"
curl -s -i -X POST -H "Jenkins-Crumb: $CRUMB" http://localhost:8080/job/FinanceMe-CI-CD/build | head -30
