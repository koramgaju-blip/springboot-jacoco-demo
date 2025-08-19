pipeline {
    agent any

    environment {
        APP_NAME = "springboot-jacoco-demo"
        DEPLOY_DIR = "D:/DevOpsDemo/jenkins"   
        JAR_FILE = "/target/springboot-jacoco-demo-0.0.1-SNAPSHOT.jar"
        PORT="9090"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/koramgaju-blip/springboot-jacoco-demo.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

      stage('Deploy') {
            steps {
                script {
                    // Stop existing java process on port
                    bat """
                    for /f "tokens=5" %%a in ('netstat -ano ^| findstr :%PORT%') do taskkill /F /PID %%a
                    """

                    // Create deploy folder
                    bat "if not exist %DEPLOY_DIR% mkdir %DEPLOY_DIR%"

                    // Copy JAR
                    bat "copy /Y %JAR_FILE% %DEPLOY_DIR%"

                    // Start new instance
                    bat """
                    cd %DEPLOY_DIR%
                    start /B java -jar %DEPLOY_DIR%\\springboot-jacoco-demo-0.0.1-SNAPSHOT.jar --server.port=%PORT%
                    """
                }
            }
        }
    }

    post {
        success {
            echo "✅ Deployment successful! Running at http://localhost:%PORT%"
        }
        failure {
            echo "❌ Deployment failed."
        }
    }
}
