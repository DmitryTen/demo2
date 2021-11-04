def buildApp() {
    sh '''mvn clean package \
            -DskipTests \
            -Ddatabase.url=jdbc:postgresql://192.168.31.141:5432/demo \
            -Ddatabase.user=vimpelcom_demo \
            -Ddatabase.password=vimpelcom_demo'''
}

def testApp() {
    sh '''mvn surefire:test'''
}

def deployMvn() {
    withCredentials([
            usernamePassword(credentialsId: 'nexus-user', usernameVariable: 'USER', passwordVariable: 'PASSWORD')
    ]){
        sh """mvn jar:jar deploy:deploy -Drepo.id=home-nexus-snapshots -Drepo.login=$USER -Drepo.pwd=$PASSWORD"""
    }
}

def deployDocker() {
    withCredentials([
            usernamePassword(credentialsId: 'nexus-user', usernameVariable: 'USER', passwordVariable: 'PASSWORD')
    ]){
        sh 'docker build -t 192.168.31.141:8083/vimpelcom-demo:1.0 .'
        sh "echo $PASSWORD | docker login -u $USER --password-stdin 192.168.31.141:8083"
//        sh "docker login -u $USER -p $PASSWORD 192.168.31.141:8083"
        sh 'docker push 192.168.31.141:8083/vimpelcom-demo:1.0'
    }
}

return this