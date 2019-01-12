pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                sh 'cd App/app && mvn clean install -DskipTests'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing../'
                sh 'cd App/app && mvn test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                // sh 'cd App/app && mvn deploy -DskipTests'
            }
        }
        stage('Merge') {
            steps {
                echo 'Merging...'
            }
        }
    }
}