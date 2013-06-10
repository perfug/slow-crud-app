#!/bin/sh

# Example run script for 

AUTH_HOST=127.0.0.1
HOD_HOST=127.0.0.1
TEST_CSV=exercices.csv
TEST_HARNESS_VERSION=0.1.0.BUILD-SNAPSHOT
TEST_HARNESS_WAR=/home/ubuntu/slow-crud-app/sca-test-harness/target/sca-test-harness-0.1.0.BUILD-SNAPSHOT-jar-with-dependencies.jar
AUTH_SERVICE_WAR=/home/ubuntu/slow-crud-app/auth-service/target/auth-0.1.0.BUILD-SNAPSHOT.war


java -Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote.local.only=false  -Dcom.sun.management.jmxremote.port=1100 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -jar jetty-runner.jar --path /auth --port 9876 $AUH_SERVICE_WAR  &
pid=$!

sleep 30

java -cp $TEST_HARNESS_WAR -DJMX_AUTH_SERVER_HOST=$AUTH_HOST -Djclarity.hod.host=$HOD_HOST com.jclarity.had_one_dismissal.Main -f $TEST_CSV
kill -9 $pid
