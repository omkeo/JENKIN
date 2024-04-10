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
               '''/opt/apache-maven-3.9.6/bin/mvn sonar:sonar \
                 -Dsonar.projectKey=stud \
                 -Dsonar.host.url=http://15.168.7.60:9000 \
                 -Dsonar.login=d1abc41e4259aeee88fcc5b379d32766a9316201 '''
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
