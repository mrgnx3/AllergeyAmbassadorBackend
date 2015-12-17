#!/usr/bin/env bash
./gradlew clean build
sudo rm /usr/share/tomcat7/webapps/aa.war
sudo rm -rf /usr/share/tomcat7/webapps/aa
sudo service tomcat7 stop
cp build/libs/AllergyAmbassador* /usr/share/tomcat7/webapps/aa.war
sudo service tomcat7 start