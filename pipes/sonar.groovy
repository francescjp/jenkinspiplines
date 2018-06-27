pipeline {
    agent any
    tools {
        maven "maven-354"
        jdk "java-8"
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/jglick/simple-maven-project-with-tests'
            }
        }
        stage('Sonar') {
            steps {
                withSonarQubeEnv('sonarlocal') {
                     sh 'mvn clean package sonar:sonar'
                }
            }
        }        
        stage('Build') {
            steps {
                sh 'mvn install'
            }
        }
    }
}
