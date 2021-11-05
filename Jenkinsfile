#!/usr/bin/env groovy

library identifier: 'jenkins-shared-libraries@master', retriever(
        [$class: 'GitSCMSource',
        remote: 'https://github.com/DmitryTen/jenkins-shared-libraries.git',
        credentials: 'github-creds']
)
def gv

pipeline {
    agent any

    tools {
        maven 'Maven 3.8.3'
    }
//    parameters {
//    }
//    environment {
//    }
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
                    mvnPackage('jdbc:postgresql://192.168.31.141:5432/demo', 'vimpelcom_demo-db')
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
        stage('deploy-maven') {
            steps {
                script {
                    mvnDeploy('home-nexus-snapshots', 'nexus-user')
                }
            }
        }
        stage('deploy-docker') {
            steps {
                script {
                    dockerBuild('192.168.31.141:8083/vimpelcom-demo:1.1')
                    dockerLogin('192.168.31.141:8083', 'nexus-user')
                    dockerPush('192.168.31.141:8083/vimpelcom-demo:1.1')
                }
            }
        }
    }
}
