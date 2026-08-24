FROM jenkins/jenkins:lts-jdk17

COPY jenkins/init.groovy.d/disable-security.groovy /usr/share/jenkins/ref/init.groovy.d/disable-security.groovy

USER root
RUN apt-get update \
    && apt-get install -y --no-install-recommends git maven docker.io docker-cli \
    && usermod -aG docker jenkins \
    && rm -rf /var/lib/apt/lists/*
USER jenkins