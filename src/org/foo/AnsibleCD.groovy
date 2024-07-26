package org.foo

class AnsibleCD implements Serializable {
    def script

    AnsibleCD(script) {
        this.script = script
    }

    def checkoutPlaybookRepo(String repoUrl, String credentialsId) {
        script.git url: repoUrl, credentialsId: credentialsId
    }

    def runAnsiblePlaybook(String playbookPath, String inventoryFile) {
        script.sh "ansible-playbook -i ${inventoryFile} ${playbookPath}"
    }

    def notifySlack(String channel, String message, String color) {
        script.slackSend(channel: channel, message: message, color: color)
    }
}

