Coding challenge by John Crickett.
## Build Your Own grep
https://codingchallenges.fyi/challenges/challenge-grep/

## Dependencies:

Picocli - https://picocli.info/

## Usage

```shell
./grep.sh "<pattern>" "<filename>"
```
`<filename>` is from `src/test/resources`.
or 
```shell
java -jar ./target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main "<pattern>" "<filename>"
```
`<filename>` is from `target/test-classes/resources`.

## Features

### Support an empty expression

#### Acceptance Criteria
**Input Data**:
```shell
./grep.sh "" essays/BFS1985.txt | diff target/test-classes/essays/BFS1985.txt -
./grep.sh "" rockbands.txt | diff target/test-classes/rockbands.txt -
./grep.sh "" symbols.txt | diff target/test-classes/symbols.txt -
./grep.sh "" test.txt | diff target/test-classes/test.txt -
```
**Output**: Each input must return an empty output.

### Match One-Letter Pattern

```shell
./grep.sh J rockbands.txt
```
**Output**:
* Judas Priest
* Bon Jovi
* Junkyard

### Support recursively listing a directory

```shell
java -jar ./target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Grep --recurse Nirvana ./src/test/resources/
java -jar ./target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Grep --recurse heaven ./src/test/resources/
```

### Support piping input from standard input
```shell
java -jar ./target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Grep --recurse Nirvana ./src/test/resources/ | java -jar ./target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Grep Madonna -
```

### Support inverse matching

```shell
java -jar ./target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Grep --recursive Nirvana ./src/test/resources/ | java -jar ./target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Grep -v Springsteen -
java -jar ./target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Grep --recursive Nirvana ./src/test/resources/ | java -jar ./target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Grep -v Madonna -
```

### Support digit and word search patterns

```shell
java -jar ./target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Grep "\d" ./src/test/resources/essays/BFS1985.txt
java -jar ./target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Grep "\d" ./src/test/resources/essays/BFS1985.txt
```

### Support beginning-of-line and end-of-line matching

```shell
java -jar ./target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Grep "^A" ./src/test/resources/rockbands.txt
java -jar ./target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Grep "na$" ./src/test/resources/rockbands.txt
```

### Support case-insensitive search

```shell
java -jar ./target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Grep A ./src/test/resources/rockbands.txt | wc -l
java -jar ./target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Grep -i A ./src/test/resources/rockbands.txt | wc -l
```
