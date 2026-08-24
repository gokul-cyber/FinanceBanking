pipeline {
    agent any
    triggers { pollSCM('H/5 * * * *') }
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
            post { success { archiveArtifacts artifacts: 'target/*.war', fingerprint: true } }
        }
        stage('Build Docker image') {
            steps { sh 'docker build -t medicure:${BUILD_NUMBER} .' }
        }
        stage('Deploy to test') {
            steps { sh 'docker stop medicure-test || true; docker rm medicure-test || true; docker run -d --name medicure-test -p 8090:8080 medicure:${BUILD_NUMBER}' }
        }
        stage('Approve production deployment') {
            steps { input message: 'Deploy this verified build to production?', ok: 'Deploy' }
        }
        stage('Deploy to production') {
            steps { sh 'docker stop medicure-prod || true; docker rm medicure-prod || true; docker run -d --name medicure-prod -p 8091:8080 medicure:${BUILD_NUMBER}' }
        }
    }
}
