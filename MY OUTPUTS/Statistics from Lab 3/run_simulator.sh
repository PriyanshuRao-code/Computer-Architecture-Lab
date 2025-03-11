#!/bin/bash

# Clean and build the JAR file
ant clean
ant make-jar

# Run the simulator for different test cases
java -jar jars/simulator.jar src/configuration/config.xml descending.txt descending.out
java -jar jars/simulator.jar src/configuration/config.xml evenorodd.txt evenorodd.out
java -jar jars/simulator.jar src/configuration/config.xml fibonacci.txt fibonacci.out
java -jar jars/simulator.jar src/configuration/config.xml palindrome.txt palindrome.out
java -jar jars/simulator.jar src/configuration/config.xml prime.txt prime.out

echo "Simulation completed successfully!"
