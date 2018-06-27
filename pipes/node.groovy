pipeline {
    agent any
    stages {
        stage('Deps') {
            steps {
        		nodejs('node8_11_3') {
         		   sh 'npm install pm2 -g' 
         		   sh 'npm install forever -g' 
        		}                
            }
        }
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
        		nodejs('node8_11_3') {
         		    sh 'pm2 start app.js' 
        		}
            }
        }                
    }
}

