pipeline {
    agent any
    
    tools{
        maven "maven"
        jdk "jdk"
    }
    environment{
        SCANNER_HOME= tool 'sonar-scanner'
    }
    stages {
        stage('Git Checkout') {
            steps {
              git changelog: false, poll: false, url: 'https://github.com/17J/onlinebookstore.git'  
            }
        }
        stage('Trivy Repo Scan') {
            steps {
              sh 'trivy repo --format table -o repo-report.html https://github.com/17J/onlinebookstore.git'
            }
        }
        stage('Compile') {
            steps {
              sh 'mvn compile'  
            }
        }
        stage('Test') {
            steps {
              sh 'mvn test'  
            }
        }
        stage('OWASP Dependency') {
            steps {
              dependencyCheck additionalArguments: '--scan ./ ', odcInstallation: 'DP'
              dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
            }
        }
        stage('Sonarqube Scan') {
            steps {
             withSonarQubeEnv('sonar') {
                    
                    sh ''' $SCANNER_HOME/bin/sonar-scanner  -Dsonar.projectName=onlinebookstore \
                           -Dsonar.java.binaries=.\
                           -Dsonar.projectKey=onlinebookstore '''
   
                    }
              }  
            }
        stage('Quality Gate') {
            steps {
              script{
                    waitForQualityGate abortPipeline: false, credentialsId: 'sonar'
              } 
            }
        }
        stage('Build') {
            steps {
              sh 'mvn package'  
            }
        }
        stage('Dokcer Push and Tag') {
            steps {
              script{
                  withDockerRegistry(credentialsId: 'docker-cred', toolName: 'docker') {
                      sh 'docker build -t onlinebooks .'
                      sh 'docker tag onlinebooks 17rj/onlinebookmart'
                }
              }
            }
        }
        stage('Image Scan') {
            steps {
              sh 'trivy image --format table -o image-report.html 17rj/onlinebookmart'  
            }
        }
        stage('Push Image') {
            steps {
              script{
                  withDockerRegistry(credentialsId: 'docker-cred', toolName: 'docker') {
                    sh 'docker push 17rj/onlinebookmart'
                }
              }  
            }
        }
        stage('Deploy Tomcat') {
            steps {
               sh "cp  /var/lib/jenkins/workspace/tomcat/target/onlinebookstore.war  /opt/apache-tomcat-9.0.65/webapps/ "
            }
        }
    }
}
