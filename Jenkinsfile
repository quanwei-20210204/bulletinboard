pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                deleteDir()
                checkout scm
            }
        }

        stage('Test'){
            steps {
                sh 'mvn verify'
            }
        }

        stage('Build & Push Docker Image'){
            steps {
                sh 'docker build -t cc-ms-k8s-training.common.repositories.cloud.sap/bulletinboard-ads-i075523 .'
                withCredentials([usernamePassword(credentialsId: 'registry-user', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh 'docker login -u $USERNAME -p $PASSWORD cc-ms-k8s-training.common.repositories.cloud.sap'
                }
                sh 'docker push cc-ms-k8s-training.common.repositories.cloud.sap/bulletinboard-ads-i075523'
            }

        }

        stage('Deployment'){
            steps {
                  withKubeConfig([credentialsId: 'kubeconfig']) {
                    sh 'kubectl apply -f app.yaml'
                  }
            }

        }
    }
}
