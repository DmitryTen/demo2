def gv

pipeline {
    agent any

    tools {
        maven 'Maven 3.8.3'
    }
    parameters {
    }
    environment {
    }
    stages {
        stage('init'){
            steps {
                script {
                    gv = load 'external_script.groovy'
                }
            }
        }
        stage('build') {
            steps {
                script {
                    gv.buildApp()
                }
            }
        }
        stage('test') {
            steps {
                script {
                    gv.testApp()
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}
