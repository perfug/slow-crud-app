#!/bin/sh

echo Startinct "Slow-crud-app_$HOSTNAME"
service tomcat7-auth stop
service tomcat7-had_one_dismissal stop
killall java
rm -Rf /opt/tomcat7-auth/logs/*
rm -Rf /opt/tomcat7-had_one_dismissal/logs/*
rm -Rf /opt/appdynamics/AppServerAgent1/logs/*
rm -Rf /opt/appdynamics/AppServerAgent2/logs/*
rm -Rf /opt/appdynamics/MachineAgent/logs/*

sed -i -e "s/<application-name>.*<\/application-name>/<application-name>slow-crud-app_$HOSTNAME<\/application-name>/" /opt/appdynamics/AppServerAgent1/conf/controller-info.xml
sed -i -e "s/<node-name>.*<\/node-name>/<node-name>$HOSTNAME<\/node-name>/" /opt/appdynamics/AppServerAgent1/conf/controller-info.xml

sed -i -e "s/<application-name>.*<\/application-name>/<application-name>slow-crud-app_$HOSTNAME<\/application-name>/" /opt/appdynamics/AppServerAgent2/conf/controller-info.xml
sed -i -e "s/<node-name>.*<\/node-name>/<node-name>$HOSTNAME<\/node-name>/" /opt/appdynamics/AppServerAgent2/conf/controller-info.xml

sed -i -e "s/<application-name>.*<\/application-name>/<application-name>slow-crud-app_$HOSTNAME<\/application-name>/" /opt/appdynamics/MachineAgent/conf/controller-info.xml
sed -i -e "s/<node-name>.*<\/node-name>/<node-name>$HOSTNAME<\/node-name>/" /opt/appdynamics/MachineAgent/conf/controller-info.xml

service tomcat7-auth start
service tomcat6-had_one_dismissal start

java -jar /opt/appdynamics/MachineAgent/machineagent.jar &

echo "Started"

