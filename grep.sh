#!/usr/bin/env bash

if [ $# -lt 2 ]; then
  echo "Usage: $0 <arg1> <arg2>"
  exit 1
fi

java -jar ./target/javagrep-1.0-SNAPSHOT.jar org.dojo.Grep "$1" "./target/test-classes/$2"
