#!/bin/sh

if [ -L $0 ] ; then
    DIR=$(dirname $(readlink -f $0)) ;
else
    DIR=$(dirname $0) ;
fi ;

AUTH_HOST=127.0.0.1
HOD_HOST=127.0.0.1
TEST_CSV=SingleThreadedExercise.csv
TEST_HARNESS_VERSION=0.1.0.BUILD-SNAPSHOT

java -cp $DIR/../../../target/sca-test-harness-$TEST_HARNESS_VERSION-jar-with-dependencies.jar -DJMX_AUTH_SERVER_HOST=$AUTH_HOST -Djclarity.hod.host=$HOD_HOST  com.jclarity.had_one_dismissal.Main -f $DIR/$TEST_CSV
