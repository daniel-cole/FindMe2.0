pipeline {

  agent any

  environment {
    SPRING_DATASOURCE_URL               = "jdbc:h2:file:./test"
    SERVER_PORT                         = "9080"
    SPRING_DATASOURCE_PASSWORD          = credentials('findme-db-password')
    JWT_SECRET                          = credentials('findme-jwt-secret')
    AWS_ACCESSKEY                       = credentials('findme-aws-access-key')
    AWS_SECRETKEY                       = credentials('findme-aws-secret-access-key')
    AWS_BUCKETNAME                      = credentials('findme-aws-bucket-name')
    PG_ADMIN_PASS                       = credentials('findme-pg-admin-pass')
    PG_APP_PASS                         = credentials('findme-pg-app-pass')
    npm_config_cache		            = "npm-cache" // required to prevent issues with file permissions in container
  }

  tools {
    jdk 'jdk8'
    maven 'M3'
   }

  stages {

    stage('Clone Repo') {
      steps {
        git 'https://github.com/daniel-cole/FindMe2.0'
      }
    }

    stage('Build') {
      steps {
        sh 'mvn clean package'
      }
    }
  }
}
