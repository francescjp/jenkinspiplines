pipeline {
    agent any
    tools {
        maven "maven-354"
        jdk "java-8"
    }
    environment {
        SAUCE_ACCESS = credentials('7d322133-312d-4e0e-bc7a-49c06b6c0ae1') //SauceLabs Credentials via Jenkins Credentials
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Gromenaware/corball-test-automation-demo.git'
		}        
	}
        stage('Test') {
            steps {
                sauce('7d322133-312d-4e0e-bc7a-49c06b6c0ae1') {
                    sh 'mvn -Dtest=WebDriverTest -Dbrowser=chrome -Dhub=http://ondemand.saucelabs.com:80/wd/hub test'
                }
            }
        }
    }
    post {
        success {
            emailext (
                    subject: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                    body: """SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]': Check console output at ${env.BUILD_URL}""",
                    to: 'guillem@gromenaware.com'
            )

        }
        failure {
            emailext (
                    subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                    body: """FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]': Check console output at ${env.BUILD_URL}""",
                    to: 'guillem@gromenaware.com'
            )
        }
        always {
            step([$class: 'XUnitBuilder',
                  thresholds: [
                          [$class: 'SkippedThreshold', failureThreshold: '0'],
                          // Allow for a significant number of failures
                          // Keeping this threshold so that overwhelming failures are guaranteed
                          //     to still fail the build
                          [$class: 'FailedThreshold', failureThreshold: '10']],
                  tools: [[$class: 'JUnitType', pattern: 'target/surefire-reports/junitreports/**']]])
            saucePublisher()
        }
    }
}
