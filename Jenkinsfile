pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps { checkout scm }
        }
        stage('Test') {
            steps { sh 'mvn -B clean test' }
            post { always { archiveArtifacts artifacts: 'target/surefire-reports/*.xml', allowEmptyArchive: true } }
        }
        stage('Package') {
            steps { sh 'mvn -B package -DskipTests' }
            post { success { archiveArtifacts artifacts: 'target/*.jar', fingerprint: true } }
        }
        stage('Build Docker image') {
            steps { sh 'docker build -t financeme:${BUILD_NUMBER} .' }
        }
        stage('Deploy to test') {
            steps { sh 'docker stop financeme-test || true; docker rm financeme-test || true; docker run -d --name financeme-test -p 8090:8080 financeme:${BUILD_NUMBER}' }
        }
        stage('Approve production deployment') {
            steps { input message: 'Deploy this verified build to production?', ok: 'Deploy' }
        }
        stage('Deploy to production') {
            steps { sh 'docker stop financeme-prod || true; docker rm financeme-prod || true; docker run -d --name financeme-prod -p 8091:8080 financeme:${BUILD_NUMBER}' }
        }
    }
}
