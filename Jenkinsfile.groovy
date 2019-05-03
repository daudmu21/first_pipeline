node{
   properties([parameters([string(defaultValue: 'IP', description: 'where should I build?', name: 'ENV', trim: false)]), pipelineTriggers([pollSCM('* * * * *')])])
   
   
   
    stage("Pull Repo"){
        git 'git@github.com:daudmu21/cool_website.git'
       
    }
    stage("webserver Install"){
        sh "ssh ec2-user@${ENV}      sudo yum install httpd -y"
    }
    stage("Index file"){
        sh "scp index.html                    ec2-user@${ENV}:/tmp"

    }
    stage("move Index"){
        sh "ssh ec2-user@${ENV}    sudo mv /tmp/index.html /var/www/html/index.html"
    }
    stage("restart httpd"){
        sh "ssh ec2-user@${ENV}      sudo systemctl restart httpd"
    }
        
}