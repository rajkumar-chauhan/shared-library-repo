// vars/ansiblePlaybook.groovy
def call(Map params) {
    def playbook = params.playbook
    def inventory = params.inventory ?: 'inventory'
    def extraVars = params.extraVars ?: ''
    def credentialsId = params.credentialsId ?: ''

    if (!playbook) {
        error 'Playbook file is required'
    }

    if (!credentialsId) {
        error 'Credentials ID is required'
    }

    withCredentials([sshUserPrivateKey(credentialsId: credentialsId, keyFileVariable: 'SSH_KEY')]) {
        sh """
            ansible-playbook -i ${inventory} ${playbook} --extra-vars "${extraVars}" --private-key \$SSH_KEY
        """
    }
}
