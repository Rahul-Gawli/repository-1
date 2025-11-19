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
              sh '''/opt/maven/bin/mvn sonar:sonar -Dsonar.projectKey=jenkins-project-1 -Dsonar.host.url=http://18.234.34.49:9000 -Dsonar.login=aabd66798c003d760d4017fc8bf067cf8a081a9b'''
          }
        }
    }
}
