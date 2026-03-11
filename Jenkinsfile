pipeline {
    agent any
    tools {
        maven 'maven3'
    }
    stages {
        stage('Checkout GitHub') {
            steps {
                git branch: 'main', url: 'https://github.com/qfunky/TransactionQA.git'
            }
        }
        stage('Run AutoTests') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
    post {
        always {
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }
}