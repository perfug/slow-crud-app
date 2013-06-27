echo Starting "Slow-crud-app_`hostname`"
service tomcat7-auth stop
service tomcat7-had_one_dismissal stop
killall java
rm -Rf /opt/tomcat7-auth/logs/*
rm -Rf /opt/tomcat7-had_one_dismissal/logs/*
rm -Rf /opt/appdynamics/AppServerAgent1/logs/*
rm -Rf /opt/appdynamics/AppServerAgent2/logs/*
rm -Rf /opt/appdynamics/MachineAgent/logs/*

sed -i -e "s/<application-name>.*<\/application-name>/<application-name>slow-crud-app_`hostname`<\/application-name>/" /opt/appdynamics/AppServerAgent1/conf/controller-info.xml
sed -i -e "s/<node-name>.*<\/node-name>/<node-name>`hostname`:9876<\/node-name>/" /opt/appdynamics/AppServerAgent1/conf/controller-info.xml

sed -i -e "s/<application-name>.*<\/application-name>/<application-name>slow-crud-app_`hostname`<\/application-name>/" /opt/appdynamics/AppServerAgent2/conf/controller-info.xml
sed -i -e "s/<node-name>.*<\/node-name>/<node-name>`hostname`:8080<\/node-name>/" /opt/appdynamics/AppServerAgent2/conf/controller-info.xml

sed -i -e "s/<application-name>.*<\/application-name>/<application-name>slow-crud-app_`hostname`<\/application-name>/" /opt/appdynamics/MachineAgent/conf/controller-info.xml
sed -i -e "s/<node-name>.*<\/node-name>/<node-name>`hostname`<\/node-name>/" /opt/appdynamics/MachineAgent/conf/controller-info.xml

service tomcat7-auth start
service tomcat7-had_one_dismissal start

java -jar /opt/appdynamics/MachineAgent/machineagent.jar >$ /opt/appdynamics/MachinesAgent/logs/out.log &

#Have to init webapp to activate JMW
echo "Please wait..."
sleep 60
wget http://localhost:8080/had_one_dismissal/ > /dev/null
wget http://localhost:9876/auth/ > /dev/null

echo "Started"

