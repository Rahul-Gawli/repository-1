pipeline {
    agent {label 'slave'}
    stages {
        stage('git_checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Rahul-Gawli/student-ui-app.git'
            }
        }
        stage('build-stage') {
            steps {
               sh '/opt/maven/bin/mvn clean package'
             
            }
        }
        stage('test-stage') {
            steps {
               withSonarQubeEnv(installationName: 'sonar', credentialsId: 'sonar-cred') {
                    sh '/opt/maven/bin/mvn sonar:sonar' 
            }
          }
        }
    }
}
