pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    options {
        timeout(time: 1, unit: 'HOURS')
        retry(3)
    }

    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'master', description: 'Branch to build')
        string(name: 'IMAGE_TAG', defaultValue: 'latest', description: 'Docker image tag')
    }

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials')
        DOCKER_IMAGE_NAME = 'vishnuprv/docker-demo'
        DOCKER_IMAGE_TAG = params.IMAGE_TAG
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Maven Project') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker --version'
                    sh "docker build -t ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} ."
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
                script {
                    sh '''
                        CONTAINER_IDS=$(docker ps -aq --filter ancestor=${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG})
                        if [ ! -z "$CONTAINER_IDS" ]; then
                            docker stop $CONTAINER_IDS
                            docker rm $CONTAINER_IDS
                        fi
                        docker run -d -p 8081:8081 ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}
                    '''
                }
            }
        }
    }

    post {
        always {
            sh 'docker logout'
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed! Check logs for details.'
        }
    }
}