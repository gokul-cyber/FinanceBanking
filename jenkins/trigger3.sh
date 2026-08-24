#!/bin/sh
COOKIES=/tmp/cookies.txt
CRUMB=$(curl -s -c $COOKIES http://localhost:8080/crumbIssuer/api/json | sed -E 's/.*"crumb":"([^"]+)".*/\1/')
echo "crumb=[$CRUMB]"
curl -s -i -b $COOKIES -X POST -H "Jenkins-Crumb: $CRUMB" http://localhost:8080/job/FinanceMe-CI-CD/build | head -20
