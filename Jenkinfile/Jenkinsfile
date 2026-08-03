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
    }
}