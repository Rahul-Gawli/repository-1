pipeline{
    agent {label 'slave'}
    
     
    
    stages {
        
    
        stage ('git_checkout') {
            steps{
                git branch: 'main', url: 'https://github.com/Rahul-Gawli/student-ui-app.git'
            }
        }
        
        
        stage ('build-stage') {
            steps{
               sh '/opt/maven/bin/mvn clean package'
            }
        }
        
        
        
        stage('test-stage') {
             steps{
                    // sh '''/opt/maven/bin/mvn sonar:sonar  -Dsonar.projectKey=student-app  -Dsonar.host.url=http://52.87.174.235:9000  -Dsonar.login=ce05780eaa062933a4b17c76ba4bbaae7ee0b974'''
                    withSonarQubeEnv(installationName: 'sonar', credentialsId: 'sonar-cred') {
                           // sh '/opt/maven/bin/mvn sonar:sonar'
                }
             }
        }

        stage('quality-gate') {
              steps{
                      timeout(10) {
                    }
                      waitForQualityGate true
              }   
        }
        
        stage ('deploy-stage') {
            steps{
                echo "deploy-success"
             }
        }
    }
}
