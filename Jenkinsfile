pipeline{
    agent any

    tools{
        maven 'Maven-3.9'
        jdk 'jdk-17'
        allure 'Allure'
    }

    stages{
        stage('Checkout'){
            steps{
                checkout scm
            }
        }

        stage('Run Selenium Tests'){
            steps{
                bat 'mvn clean test'
            }
        }
    }

    post{
        always{
            allure includeProperties:false, jdk:'', results: [[path:'allure-results']]
        }
        failure{
            emailext body:"The build failed! Check logs here: ${env.BUILD_URL}",
            subject: "FAILED: Job  ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
            to: 'zhonglisha8@gmail.com'
        }
    }
}