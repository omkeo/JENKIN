pipeline {
    agent any

    stages {
        stage('Pull') {
            steps {
                git changelog: false, poll: false, url: 'https://github.com/chetansomkuwar254/studentapp.ui'
                echo 'Yes, Application repository pull is done !'
            }
        }
        stage('Build') {
            steps {
                sh '/opt/apache-maven-3.9.6/bin/mvn clean package'
                echo 'Yes, Application Build is done !'
            }
        }
        stage('Test') {
            steps {
                sh '''/opt/apache-maven-3.9.6/bin/mvn sonar:sonar \\
                        -Dsonar.projectKey=student \\
                        -Dsonar.host.url=http://13.208.169.87:9000 \\
                        -Dsonar.login=d1443564cb1ff774fc8e208c89fe9ab4bef53743'''
            }
        }
        stage('Quality Test') {
            steps {
                echo 'Here we are testing the quality'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploy Done'
            }
        
        }
    }
}
