pipeline {
  agent {
    docker {
        image 'gradlewrapper:latest'
        args '-v gradlecache:/gradlecache'
    }
  }
  environment {
      GRADLE_ARGS = '-Dorg.gradle.daemon.idletimeout=5000'
  }

  stages {
    stage('fetch') {
      steps {
        git(url: 'https://github.com/cpw/eventbus.git', changelog: true)
      }
    }
    stage('buildandtest') {
      steps {
        sh './gradlew ${GRADLE_ARGS} --refresh-dependencies --continue build test'
      }
    }
    stage('publish') {
      environment {
        FORGE_MAVEN = credentials('forge-maven-forge-user')
      }
      steps {
        sh './gradlew ${GRADLE_ARGS} publish -PforgeMavenUser=${FORGE_MAVEN_USR} -PforgeMavenPassword=${FORGE_MAVEN_PSW}'
        sh 'curl --user ${FORGE_MAVEN} http://files.minecraftforge.net/maven/manage/promote/latest/net.minecraftforge.eventbus/${BUILD_NUMBER}'
      }
    }
  }
  post {
    always {
      archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
      junit 'build/test-results/test/*.xml'
      jacoco sourcePattern: '**/src/*/java'
    }
  }
}