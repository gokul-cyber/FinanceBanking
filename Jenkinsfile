pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps { checkout scm }
        }
        stage('Test') {
            steps { sh 'mvn -B clean test' }
            post { always { junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml' } }
        }
        stage('Package') {
            steps { sh 'mvn -B package -DskipTests' }
            post { success { archiveArtifacts artifacts: 'target/*.jar', fingerprint: true } }
        }
        stage('Build Docker image') {
            steps { sh 'docker build -t financeme:${BUILD_NUMBER} .' }
        }
        stage('Deploy to test') {
            when { branch 'main' }
            steps { sh 'docker stop financeme-test || true; docker rm financeme-test || true; docker run -d --name financeme-test -p 8080:8080 financeme:${BUILD_NUMBER}' }
        }
        stage('Approve production deployment') {
            when { branch 'main' }
            steps { input message: 'Deploy this verified build to production?', ok: 'Deploy' }
        }
        stage('Deploy to production') {
            when { branch 'main' }
            steps { sh 'docker stop financeme-prod || true; docker rm financeme-prod || true; docker run -d --name financeme-prod -p 8081:8080 financeme:${BUILD_NUMBER}' }
        }
    }
}
