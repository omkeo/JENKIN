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
                       -Dsonar.projectKey=my-project \
                       -Dsonar.host.url=http://15.168.11.73:9000 \
                        -Dsonar.login=bc6a02cac9fe27398f76cb56eb4ec88a4ee8386a '''
            }
        }
        stage('Deploy') {
            steps {
              sh 'sudo cp /root/my-app/target/my-app-1.0-SNAPSHOT.jar /root/apache-tomcat-10.1.20/webapps/ '
              echo 'Deploy Done'
            }
        
        }
    }
}
