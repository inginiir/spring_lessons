#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i /home/inginiir/.ssh/sweater1.pem \
      target/spring_lessons-1.0-SNAPSHOT.jar \
      ec2-user@ec2-3-10-118-38.eu-west-2.compute.amazonaws.com:/home/ec2-user/

echo 'Restart server...'

ssh -i /home/inginiir/.ssh/sweater1.pem ec2-user@ec2-3-10-118-38.eu-west-2.compute.amazonaws.com << EOF

pgrep java | xargs kill -9
nohup java -jar  spring_lessons-1.0-SNAPSHOT.jar > log.txt &

EOF

echo 'Bye!'

