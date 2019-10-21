pipeline {
    agent any


    stages {
        stage('Compile') {
            steps {
                echo "Compiling..."
                sh "${tool name: 'sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt compile"
            }
        }

        stage('Package') {
            steps {
                echo "Packaging..."
                sh "${tool name: 'sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt package"
            }
        }
        stage('Unit Test') {
            steps {
                echo "Testing..."
                sh "${tool name: 'sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt test"
            }
        }
    }
}
