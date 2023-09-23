pipeline {
    agent any
    
    tools{
        jdk "jdk17"
        maven "maven3"
    } 
    environment{
        SONAR_HOME = tool "sonar-scanner"
    }
    stages {
        stage('Git CheckOut') {
            steps {
              git branch: 'main', url: 'https://github.com/DevOpsWorld-9959/javaProject.git'
            }
        }
        stage("Code Compile"){
            steps{
                sh "mvn compile"
            }
        }

        stage("Trivy scan"){
            steps{
                sh "trivy fs ."
            }
        }
        stage("Testing"){
            steps{
                sh "mvn test"
            }
        }        
       stage("SonarQube Analysis"){
            steps{
             
              withSonarQubeEnv('sonar'){
                sh"$SONAR_HOME/bin/sonar-scanner    -Dsonar.projectName=Ekart -Dsonar.projectKey=Ekart -Dsonar.java.binaries=. "
              }
            }
        }
        stage("ArtifactBuild"){
            steps{
                sh "mvn clean package -DskipTests=true"
            }
        }
        stage("DeployingArtifact"){
            steps{
               configFileProvider([configFile(fileId: '26b03cc5-6fb1-47f0-87d1-aafb52572e2f', targetLocation: 'myartigactdeploy', variable: 'myartigactdeploy')]) {
                  sh "mvn -s $myartigactdeploy clean  deploy -DskipTests=true"
                }
            }
        }
  
 
    }
}
