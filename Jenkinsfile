pipeline {
    agent any
        boolean buildPassed = true

    stages {
        stage('Build') {
            try {
                echo "Building..."
                sh "${tool name: 'sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt compile"
                sh "${tool name: 'sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt package"
            }
            catch (Exception e){
                buildPassed = false
            }
        }
        stage('Unit Test') {
            if(buildPassed) {
                echo "Testing..."
                sh "${tool name: 'sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt test"
            }
        }
    }
}
