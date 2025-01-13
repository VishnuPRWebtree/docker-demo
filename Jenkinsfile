pipeline {
    agent any

        tools {
            maven 'Maven3'
            jdk 'Java21'
        }

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials')
        DOCKER_IMAGE_NAME = 'vishnuprv/docker-demo'
        DOCKER_IMAGE_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                // Get code from GitHub repository
                git branch: 'master', url: 'https://github.com/VishnuPRWebtree/docker-demo.git'
            }
        }

        stage('Build Maven Project') {
            steps {
                // Build Spring Boot application
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker -version'
                    // Build Docker image
                    docker.build("${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}")
                }
            }
        }

        stage('Login to DockerHub') {
            steps {
                sh 'echo $DOCKER_HUB_CREDENTIALS_PSW | docker login -u $DOCKER_HUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Push to DockerHub') {
            steps {
                sh "docker push ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
            }
        }

        stage('Deploy') {
            steps {
                sh """
                    docker stop \$(docker ps -q --filter ancestor=${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}) || true
                    docker rm \$(docker ps -aq --filter ancestor=${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}) || true
                    docker run -d -p 8080:8080 ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}
                """
            }
        }
    }

    post {
        always {
            sh 'docker logout'
        }
    }
}