pipeline {
    agent any

    stages {

        stage('Compile') {
            steps {
                echo "Compiling..."
                sh "/usr/bin/sbt compile"
            }
        }

        stage('Package') {
            steps {
                echo "Packaging..."
                sh "/usr/bin/sbt package"
            }
        }

        stage('Test') {
            steps {
                echo "Testing..."
                sh "/usr/bin/sbt test"
            }
        }

    }
}
