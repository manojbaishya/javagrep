Coding challenge by John Crickett.
## Build Your Own grep
https://codingchallenges.fyi/challenges/challenge-grep/

## Dependencies:

Picocli - https://picocli.info/

## Usage

```shell
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main "<pattern>" "<filename>"
```
`<filename>` is from `target/test-classes/`.

## Features

### Support an empty expression

#### Acceptance Criteria
**Input**:
```shell
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main "" target/test-classes/essays/BFS1985.txt | diff target/test-classes/essays/BFS1985.txt -
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main "" target/test-classes/rockbands.txt | diff target/test-classes/rockbands.txt -
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main "" target/test-classes/symbols.txt | diff target/test-classes/symbols.txt -
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main "" target/test-classes/artofwar.txt | diff target/test-classes/artofwar.txt -
```
**Output**:
```

```

### Match One-Letter Pattern
**Input**:
```shell
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main J target/test-classes/rockbands.txt
```
**Output**:
```
Judas Priest
Bon Jovi
Junkyard
```

### Support recursively listing a directory

**Input**:
```shell
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main --recursive Nirvana src/test/resources
```

**Output**:
```
rockbands.txt:Nirvana
essays/BFS1985.txt:Since Bruce Springsteen, Madonna, way before Nirvana
essays/BFS1985.txt:On the radio was Springsteen, Madonna, way before Nirvana
essays/BFS1985.txt:And bring back Springsteen, Madonna, way before Nirvana
essays/BFS1985.txt:Bruce Springsteen, Madonna, way before Nirvana
```

**Input**:
```shell
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main --recursive heaven src/test/resources
```

**Output**:
```
essays/coffee.txt:And still a heaven of brass inflames the burning hours.
essays/coffee.txt:Spreading branches, nor lifts a lofty top to heaven;
essays/coffee.txt:That human laws were never made in heaven;
essays/coffee.txt:     heaven for Coffee, for see how many blessings are concentrated in
essays/coffee.txt:  Now mounted up to heaven again,
artofwar.txt:heaven, is it not right that those in authority should value and be
artofwar.txt:heaven.
artofwar.txt:skilled in attack flashes forth from the topmost heights of heaven [see
artofwar.txt:thereby succeed in winning, may be called a heaven-born captain.
artofwar.txt:and hammering on bronze vessels, until heaven and earth were convulsed
artofwar.txt:affairs of men, the seasons of heaven and the natural advantages of
```

### Support bash compatible glob patterns

**Input**:
```shell
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main biograph target/test-classes/{*,**/*}.txt
```

**Output**:
```
Ssu-ma Ch’ien gives the following biography of Sun Tzŭ: [1]
this chapter. But he proceeds to give a biography of his descendant,
biography were false and untrustworthy? The answer, I fear, must be in
Kung’s preface to his edition, and the biography of Sun Tzŭ from the
man, whose biography in the _San Kuo Chih_ reads like a romance. One of
48. A notable person in his day. His biography is given in the _San Kuo
[1] See Col. Henderson’s biography of Stonewall Jackson, 1902 ed., vol.
from. It is not given in the biography of Li Hsiung or that of his
English prose; Oliver Goldsmith; Boswell, the biographer; Burke, the
most delightful biography in all literature. Boswell's taste for
     dine here with Wilkie. In his "Autobiography," in the year 1808,
_biog._       "     biography
_Autobiography_, Haydon, _q._, 583
```

### Support piping input from standard input
**Input**:
```shell
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main --recursive Nirvana target/test-classes | java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main Madonna -
```

**Output**:
```
essays/BFS1985.txt:Since Bruce Springsteen, Madonna, way before Nirvana
essays/BFS1985.txt:On the radio was Springsteen, Madonna, way before Nirvana
essays/BFS1985.txt:And bring back Springsteen, Madonna, way before Nirvana
essays/BFS1985.txt:Bruce Springsteen, Madonna, way before Nirvana
```

### Support inverse matching

```shell
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main --recursive Nirvana src/test/resources | java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main -v Springsteen -
```

```

```

```shell
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main --recursive Nirvana src/test/resources | java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main -v Madonna -
```

### Support digit and word search patterns

```shell
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main "\d" src/test/resources/essays/BFS1985.txt
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main "\d" src/test/resources/essays/BFS1985.txt
```

### Support beginning-of-line and end-of-line matching

```shell
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main "^A" src/test/resources/rockbands.txt
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main "na$" src/test/resources/rockbands.txt
```

### Support case-insensitive search

```shell
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main A src/test/resources/rockbands.txt | wc -l
java -jar target/javagrep-1.0-SNAPSHOT.jar org.dojo.grep.Main -i A src/test/resources/rockbands.txt | wc -l
```
