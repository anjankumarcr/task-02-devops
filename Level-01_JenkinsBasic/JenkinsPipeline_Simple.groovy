pipeline {
    agent any

    stages {

        stage('Clone Repo') {
            steps {
                git "https://github.com/Become-DevOps/proj.git"
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t simple-image:latest .'
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                docker rm -f simple-container || true
                docker run -d --name simple-container -p 9090:80 simple-image:latest
                '''
            }
        }

        stage('Show Containers') {
            steps {
                sh 'docker ps'
            }
        }
    }
}
