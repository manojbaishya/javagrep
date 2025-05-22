#!/usr/bin/env bash

# Support an empty expression
./grep.sh "" essays/BFS1985.txt | diff target/test-classes/essays/BFS1985.txt -
./grep.sh "" rockbands.txt | diff target/test-classes/rockbands.txt -
./grep.sh "" symbols.txt | diff target/test-classes/symbols.txt -
./grep.sh "" test.txt | diff target/test-classes/test.txt -

# Match One-Letter Pattern

./grep.sh J rockbands.txt
