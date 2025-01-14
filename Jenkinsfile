pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'Java21'
    }

    environment {
        JAR_FILE = 'target/demo-0.0.1-SNAPSHOT.jar' // Path to the JAR file
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/VishnuPRWebtree/docker-demo.git'
            }
        }

        stage('Build Maven Project') {
            steps {
                script {
                    echo 'Building the Maven project...'
                    sh 'mvn clean install package -DskipTests'
                }
            }
        }

        stage('Run JAR File') {
            steps {
                script {
                    echo 'Running the JAR file...'
                    sh """
                        java -jar ${JAR_FILE}
                    """
                }
            }
        }
    }

    post {
        always {
            script {
                echo 'Pipeline execution completed.'
            }
        }
    }
}
