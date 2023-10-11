pipeline {
    agent any
    
    tools{
        jdk "jdk17"
        maven "maven3"
    } 
    environment{
        SONAR_HOME = tool "sonar-scanner"
        mvnHome = tool "maven3"
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
     /*
        stage("Parallel Execution OWAST/TRIVY"){
            
          parallel{
              
               stage("OWASP dependency Check"){
                    steps{
                      dependencyCheck additionalArguments: '--scan ./',odcInstallation:'DC'
                      dependencyCheckPublisher pattern:'**/dependency-check-report.xml'                   
                    }
                }        
        
                stage("Trivy scan"){
                    steps{
                        sh "trivy fs ."
                    }
                } 
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
                  sh "$mvnHome/bin/mvn sonar:sonar"
               // sh "$SONAR_HOME/bin/sonar-scanner  -Dsonar.host.url=http://localhost:9001/   -Dsonar.projectName=Ekart -Dsonar.projectKey=Ekart -Dsonar.java.binaries=. "
              }
            }
        }

        stage("Quality Gates"){
            steps{
              script{
                 timeout(time:1,unit:"HOURS"){
                     def qg = waitForQualityGate()
                     if(qg.status !=  "OK"){
                         error "Pipeline aborted due to quality gates failed."
                     }
                 }
              }
            }
        }
*/
        stage("ArtifactBuild"){
            steps{
                sh "mvn clean package -DskipTests=true"
            }
        }
        /*
        stage("DeployingArtifact"){
            steps{
               configFileProvider([configFile(fileId: '26b03cc5-6fb1-47f0-87d1-aafb52572e2f', targetLocation: 'myartigactdeploy', variable: 'myartigactdeploy')]) {
                  sh "mvn -s $myartigactdeploy clean  deploy -DskipTests=true"
                }
            }
        }
        */
        stage("Build Docker Image"){
            steps{
                script{
                    def imageName = "kirankumarbandari/myappdocker"
                    env.imageName = "${imageName}"
                    def oldImageID = sh( 
                                            script: 'docker images -qf reference=\${imageName}:latest}',
                                            returnStdout: true
                                        )
                    
                    withDockerRegistry(credentialsId: 'DOCKER', toolName: 'docker') {
                        
                        if('${oldImageID}' != ''){
                           sh "docker rmi -f kirankumarbandari/myappdocker:latest"
                           sh "docker rmi -f myappdocker"
                           sh "docker build -t myappdocker -f  docker/Dockerfile  ."
                           sh "docker tag myappdocker kirankumarbandari/myappdocker:latest"                           
                        }else{
                           sh "docker build -t myappdocker -f  docker/Dockerfile  ."
                           sh "docker tag myappdocker kirankumarbandari/myappdocker:latest"                                                    
                            
                        }
                        

                    }
                }
            }
        }
        stage("Push Image Docker Registry"){
            steps{
                script{
                    withDockerRegistry(credentialsId: 'Docker', toolName: 'docker') {
                        sh "docker  push  kirankumarbandari/myappdocker:latest"
                    }
                }
            }
        }  
 
    }
}
