# ScaGS

Scala implementation of the Gale-Shapley algorithm

## What is ScaGS ?

ScaGS is a [Scala](https://www.scala-lang.org/) implementation of the seminal Gale-Shapley algorithm to solve the 
[Stable Marriage Problem](https://en.wikipedia.org/wiki/Stable_marriage_problem) (SMP). 
The problem consists of finding a stable matching between two equally sized sets of elements given an ordering 
of preferences for each element. A matching is a mapping from the elements of one set to the elements of the other set.  

## Requirements

In order to run the demonstration you need:

- the Java virtual machine [JVM 1.8.0_60](http://www.oracle.com/technetwork/java/javase/downloads/index.html).

In order to compile the code you need:

- the programming language [Scala 2.12.4](http://www.scala-lang.org/download/);

- the interactive build tool [SBT 1.2.1](http://www.scala-sbt.org/download.html).

## Usage

    java -jar ScaGS.jar filename

Examples of file describing a SMP are [smp1.pl](examples/smp1.pl), [smp2.pl](examples/smp2.pl) or [smp3.pl](examples/smp3.pl).

## Installation

Compile

    sbt compile

then

    sbt "run filename"

and eventually

    sbt assembly


## Contributors

Copyright (C) Maxime MORGE 2019

## License

This program is free software: you can redistribute it and/or modify it under the terms of the
GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.
If not, see <http://www.gnu.org/licenses/>.
