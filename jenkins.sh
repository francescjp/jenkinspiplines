#!/bin/bash
set -eux
wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add -
sudo apt-add-repository "deb https://pkg.jenkins.io/debian-stable binary/"
apt-get update
apt-get -y install openjdk-8-jre openjdk-8-jdk
apt-get -y install jenkins
systemctl start jenkins
