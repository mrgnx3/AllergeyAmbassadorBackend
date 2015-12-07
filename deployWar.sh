#!/usr/bin/env bash
sudo rm /usr/local/Cellar/tomcat7/7.0.63/libexec/webapps/aa.war
sudo rm -rf /usr/local/Cellar/tomcat7/7.0.63/libexec/webapps/aa
sudo /usr/local/Cellar/tomcat7/7.0.63/bin/catalina stop
cp build/libs/AllergyAmbassador* /usr/local/Cellar/tomcat7/7.0.63/libexec/webapps/aa.war
sudo /usr/local/Cellar/tomcat7/7.0.63/bin/catalina start