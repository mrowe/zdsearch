# ZD-Search


# Building

To build, package and run `zd-search` you will need:

 * Java 9 (I used `Java(TM) SE Runtime Environment (build 9+181)`)
 * Gradle >= 4.2 (I used `4.2.1`)

I have built and tested the program on a Mac running MacOS 10.13. It _should_ work on any Unix-like operating system, but I haven't tested any others.

## To run the tests:

```
$ gradle test
```

## To build a JAR:

```
$ gradle jar
```

# Running

## To run the program:

First build a JAR (as above). Then invoke it:

```
$ java -jar build/libs/zd-search-1.0-SNAPSHOT.jar
```
