#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

!!!!!scp  ssh \
      target/spring_lessons-1.0-SNAPSHOT.jar \
      user@server:home/!!!! folder on server

echo 'Restart server...'

!!!!! !!!!!scp  ssh  user@server << EOF

pgrep java |  | xargs kill -9
nohup java -jar  spring_lessons-1.0-SNAPSHOT.jar > log.txt &

EOF

echo 'Bye!'

