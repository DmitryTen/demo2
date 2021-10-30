def buildApp() {
    sh '''mvn clean package 
            -Ddatabase.url=jdbc:postgresql://192.168.31.141:5432/demo 
            -Ddatabase.user=vimpelcom_demo 
            -Ddatabase.password=vimpelcom_demo'''
}

def testApp() {
    echo 'test'
}

def deployApp() {
    echo 'deploy'
}

return this