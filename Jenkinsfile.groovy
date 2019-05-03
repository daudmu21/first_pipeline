node{
    properties([parameters([string(defaultValue: 'IP', description: 'Where\'s should I build', name: 'Environment ', trim: true)]), pipelineTriggers([pollSCM('* * * * *')])])
    stage("Pull Repo"){
        git 'git@github.com:daudmu21/cool_website.git'
    }
    stage("Webserver Install"){
        sh "ssh  ec2-user@${ENV}  sudo yum install httpd -y"
    }
    stage("Index file"){
        sh "scp index.html  ssh  ec2-user@${ENV}:/tmp"
    }
    stage("Move Index"){
        sh 'ssh  ec2-user@${ENV}   "sudo mv /tmp/index.html /var/www/html/index.html"'
    }
    stage("Start HTTPD"){
        sh "ssh  ec2-user@${ENV}   sudo systemctl restart httpd"
    }
}