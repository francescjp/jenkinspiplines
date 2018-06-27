pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://bitbucket.org/itnove/the-example-app-nodejs'
		    }        
        }
        stage('Build') {
            steps {
        		nodejs('node8_11_3') {
         		   sh 'npm install' 
        		}
            }
        }
        stage('Test') {
            steps {
        		nodejs('node8_11_3') {
         		   sh 'npm run test:unit' 
         		   sh 'npm run test:integration' 
        		}
            }
        }        
        stage('Deploy') {
            steps {
                sh 'docker build -t the-example-app.nodejs .'
                sh 'docker run -p 3000:3000 -d the-example-app.nodejs'
            }
        }                
    }
}

