#!/bin/bash

# Clean and build the JAR file
ant clean
ant make-jar

# Path to the assembler JAR file
ASSEMBLER_JAR="jars/assembler.jar"

# Assemble .asm files into .out files
java -Xmx1g -jar "$ASSEMBLER_JAR" descending.asm descending.out
java -Xmx1g -jar "$ASSEMBLER_JAR" even-odd.asm evenorodd.out
java -Xmx1g -jar "$ASSEMBLER_JAR" fibonacci.asm fibonacci.out
java -Xmx1g -jar "$ASSEMBLER_JAR" palindrome.asm palindrome.out
java -Xmx1g -jar "$ASSEMBLER_JAR" prime.asm prime.out

echo "Assembly completed successfully!"
