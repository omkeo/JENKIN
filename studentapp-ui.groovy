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
                     -Dsonar.projectKey=new-pro \
                     -Dsonar.host.url=http://15.168.37.55:9000 \
                     -Dsonar.login=96a6b8d605d1afe1f6de0af758cea78bc4536cc6 '''
            }
        }
        stage('Deploy') {
            steps {
             sh " sudo cp /var/lib/jenkins/workspace/PROJECT/target/studentapp-2.2-SNAPSHOT.war   /root/apache-tomcat-10.1.20/webapps/ "
              echo 'Deploy Done'
            }
        }
    }
}
