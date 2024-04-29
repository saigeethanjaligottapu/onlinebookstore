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
        stage('Deploy Tomcat') {
            steps {
               sh "cp  /var/lib/jenkins/workspace/tomcat/target/onlinebookstore.war  /opt/apache-tomcat-9.0.65/webapps/ "
            }
        }
    }
}
