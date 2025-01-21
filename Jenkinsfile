pipeline {
  agent any

  tools {
    maven 'Maven3'
    jdk 'Java21'
  }

  environment {
    DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials')
    DOCKER_IMAGE_NAME = 'vishnuprv/docker-demo'
    DOCKER_IMAGE_TAG = "${BUILD_NUMBER}" // Using Jenkins BUILD_NUMBER
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'master',
          url: 'https://github.com/VishnuPRWebtree/docker-demo.git'
      }
    }

    stage('Build Maven Project') {
      steps {
        sh 'mvn clean install package -DskipTests'
      }
    }

    stage('Clean Old Docker Resources') {
      steps {
        sh ''
        '
        docker ps - a - q--filter ancestor = $ {
          DOCKER_IMAGE_NAME
        } | xargs - r docker rm - f

        docker images $ {
          DOCKER_IMAGE_NAME
        } - q | tail - n + 4 | xargs - r docker rmi - f

        docker image prune - f ''
        '
      }
    }

    stage('Build Docker Image') {
      steps {
        script {
          def IMAGE_TAG = BUILD_NUMBER ? : "latest"
          sh ""
          "
          docker--version
          docker build--build - arg BUILD_NUMBER = $ {
              IMAGE_TAG
            }\ -
            t $ {
              DOCKER_IMAGE_NAME
            }: $ {
              IMAGE_TAG
            }.
          ""
          "
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
        sh ''
        '
        docker push $ {
          DOCKER_IMAGE_NAME
        }: $ {
          DOCKER_IMAGE_TAG
        }
        ''
        '
      }
    }

    stage('Deploy') {
      steps {
        sh ''
        '
        export BUILD_NUMBER = $ {
          BUILD_NUMBER
        }
        docker - compose down || true
        docker - compose up - d

        docker system prune - f ''
        '
      }
    }
  }

  post {
    always {
      sh ''
      '
      docker logout

      docker image prune - f
      docker container prune - f ''
      '
    }
  }
}