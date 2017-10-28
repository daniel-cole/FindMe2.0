pipeline {

    environment {
        SPRING_DATASOURCE_URL = "jdbc:h2:file:./build/test"
        SPRING_DATASOURCE_USERNAME = "sa"
        SPRING_DATASOURCE_PASSWORD = ""
        JWT_SECRET = credentials('findme-jwt-secret')
        AWS_ACCESSKEY = credentials('findme-aws-access-key')
        AWS_SECRETKEY = credentials('findme-aws-secret-access-key')
        AWS_BUCKETNAME = credentials('findme-aws-bucket-name')
        npm_config_cache = "npm-cache" // required to prevent issues with file permissions in container
    }

    agent any

    tools {
        jdk 'jdk8'
        maven 'M3'
    }

    stages {

        stage('Clone FindMe Repo') {
            steps {
                git 'https://github.com/daniel-cole/FindMe2.0'
            }
        }

        stage('Build FindMe War') {
            steps {
                sh 'mvn clean package'
            }
        }


        stage('Build FindMe Image') {
            steps {
                script {
                    jarFile = sh(
                        script: 'echo target/$(ls target/ | egrep "findme.*\\.jar$")',
                        returnStdout: true
                    ).trim()
                }

                script {
                    imageId = sh(
                        script: "docker build -q --build-arg FINDME_JAR=${jarFile} . -t thekingwizard/findme:lts",
                        returnStdout: true
                    ).trim()
                }
            }
        }

        stage('Push FindMe Image') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: 'https://index.docker.io/v1/']) {
                    sh 'docker push thekingwizard/findme:lts'
                    sh "docker rmi ${imageId}"
                }
            }
        }
    }

    post {
        success {
            archive "target/*.jar"
        }
    }
}
