#!/bin/bash

####################################################
# DO NOT CHANGE THE GRADLE OPTIONS IN THE BLOCK    #
# BELOW, IT WILL HAVE IMPACT ON THE PERFORMANCE    #
# OF YOUR APPLICATION                              #
####################################################
GRADLE_OPTS="-Dgradle.user.home=~/gradle_cache"    #
####################################################



./gradlew clean bootrun &

pip3 install pymongo

  # upload questions
  echo "Uploading questions..."
  python3 ~/Documents/load_questions.py
while ! netstat -tna | grep 'LISTEN\>' | grep -q ':8080\>'; do
  echo "waiting for spring application to start"
  sleep 2 # time in seconds, tune it as needed
  

done

# If you have any script to load the data make sure that its part of this bash script.


