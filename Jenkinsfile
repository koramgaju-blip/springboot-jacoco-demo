pipeline {
    agent any

    environment {
        APP_NAME = "springboot-jacoco-demo"
        DEPLOY_DIR = "D:\\DevOpsDemo\\jenkins\\"   
        JAR_FILE = "target\\springboot-jacoco-demo-0.0.1-SNAPSHOT.jar"
        PORT="9090"
    }
    
    triggers {
        cron('H/59 * * * *')   // every 59 minutes
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/koramgaju-blip/springboot-jacoco-demo.git'
            }
        }
        
  		stage('Run Tests') {
            steps {
                bat "mvn clean test"
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
                    //bat """
                    // for /f "tokens=5" %a in ('netstat -ano ^| findstr :9090') do taskkill /F /PID %a
                    //"""

                    // Create deploy folder
                    bat "if not exist %DEPLOY_DIR% mkdir %DEPLOY_DIR%"

                    // Copy JAR
                    bat "copy /Y %JAR_FILE% %DEPLOY_DIR%"

                    // Start new instance
                    bat """
                    cd %DEPLOY_DIR%
                    start /B java -jar %DEPLOY_DIR%\\springboot-jacoco-demo-0.0.1-SNAPSHOT.jar --server.port=9090
                    """
                }
            }
        }
    }

    post {
        success {
	        emailext(
	            subject: "📢 TEST STAGE RESULT - ${currentBuild.currentResult}",
	            body: "The TEST stage finished with result: ${currentBuild.currentResult}",
	            to: 'qa-team@example.com'
	        )
	        echo "✅ Deployment successful! Running at http://localhost:%PORT%"
        }
        failure {
            echo "❌ Deployment failed."
        }
    }
}
