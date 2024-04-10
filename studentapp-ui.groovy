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
               sh ''' /opt/apache-maven-3.9.6/bin/mvn sonar:sonar \
                 -Dsonar.projectKey=stud \
                 -Dsonar.host.url=http://15.168.11.73:9000 \
                 -Dsonar.login=d1abc41e4259aeee88fcc5b379d32766a9316201 '''
            }
        }
        stage('Deploy') {
            steps {
                sh "/var/lib/jenkins/workspace/studentapp-ui/target/studentapp-2.2-SNAPSHOT.war  /root/apache-tomcat-10.1.20/webapps"
                echo 'Deploy Done'
            }
        
        }
    }
}
