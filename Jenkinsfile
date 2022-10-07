pipeline{
  agent any
  stages{
    stage{'git repo & clean'){
      steps{
        bat "rmdir /s /q travel
        bat "git clone https://github.com/njoya57/travel.git"
        bat "mvn clean -f travel"
      }
    }
    stage('install'){
      steps{
        bat "mvn install -f travel"
      }
    }
    stage('test'){
      steps{
        bat "mvn package -f travel"
      }
    }
  }
}
