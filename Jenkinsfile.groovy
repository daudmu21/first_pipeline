node{
    stage("Pull Repo"){
        git 'git@github.com:daudmu21/cool_website.git'
    }
    stage("Webserver Install"){
        sh "ssh ec2-user@54.171.239.132  sudo yum install httpd -y"
    }
    stage("Index file"){
        sh "scp index.html  ec2-user@54.171.239.132:/tmp"
    }
    stage("Move Index"){
        sh 'ssh ec2-user@54.171.239.132   "sudo mv /tmp/index.html /var/www/html/index.html"'
    }
    stage("Start HTTPD"){
        sh "ssh ec2-user@54.171.239.132   sudo systemctl restart httpd"
    }
}