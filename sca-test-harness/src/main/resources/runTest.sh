#!/bin/sh

# Example run script for 

AUTH_HOST=192.168.2.233
HOD_HOST=192.168.2.82
TEST_CSV=test.csv
TEST_HARNESS_VERSION=0.1.0.BUILD-SNAPSHOT

java -Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote.local.only=false  -Dcom.sun.management.jmxremote.port=1100 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -jar jetty-runner.jar --path /auth --port 9876 auth-LATEST.war &
pid=$!

sleep 30

java -cp sca-test-harness-$TEST_HARNESS_VERSION-jar-with-dependencies.jar -DJMX_AUTH_SERVER_HOST=$AUTH_HOST -Djclarity.hod.host=$HOD_HOST com.jclarity.had_one_dismissal.Main -f $TEST_CSV
kill -9 $pid