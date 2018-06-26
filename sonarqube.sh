#!/bin/bash
set -eux

apt-get update
apt-get -y install openjdk-8-jre openjdk-8-jdk
wget https://downloads.sourceforge.net/project/sonar-pkg/deb/binary/sonar_6.7.4_all.deb
dpkg -i sonar_6.7.4_all.deb
sudo service sonar start
