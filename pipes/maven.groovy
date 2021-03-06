pipeline {
    agent any
    tools {
        maven "maven-354"
    }
    stages {
       stage('Preparation') { // for display purposes
        steps {
          git 'https://github.com/jglick/simple-maven-project-with-tests.git'  
        }
       }
       stage('Build') {
           steps {
             sh "mvn -Dmaven.test.failure.ignore clean package"      
           }
       }
       stage('Results') {
           steps{
              junit '**/target/surefire-reports/TEST-*.xml'
              archiveArtifacts 'target/*.jar'               
           }
        }
        stage('Deploy') {
            steps {
                input message: 'Proceed or abort?'
            }
        }
    }
}
