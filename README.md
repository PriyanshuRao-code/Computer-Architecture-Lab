## Computer-Architecture-Lab
The given below are some commands used. But to be sure about the format and extensive detailing read the Lab handouts present in every lab. <br>
ToyRISC specifications are available in Lab 1. <br><br>

# Lab 1 - All the asm files. <br>
To test your program, use the given Java application emulator.jar. The command is &nbsp; &nbsp; &nbsp; java -jar <path-to-emulator.jar><path-to-assembly-file><starting-address><ending-address> <br>
One test case for each program is given. To evaluate, the command is &nbsp; &nbsp; &nbsp; python test_each.py <path-to-assembly-file> <br>
To test your zip archive, the command is &nbsp; &nbsp; &nbsp; python test_zip.py <path-to-zip-archive> <br><br>

# Lab 2 - Assembler for the ToyRISC ISA. <br>
instructions - 
ant make-jar &nbsp; &nbsp; (For making jar file) (A jar file is created at the location jars/assembler.jar) <br>
To run your program, run this command on the terminal: &nbsp; &nbsp; &nbsp;  java -Xmx1g -jar <path-to-jar-file> <path-to-assembly-program> <path-to-object-file> <br>
Run the script &nbsp; &nbsp; &nbsp; python test zip.py <path-to-zip-file>  &nbsp; &nbsp; &nbsp; in the same folder that contains build.xml, test cases, and test zip.py. <br><br>

# lab 3 - Single Cycle Processor. <br>
For printing the statistics: &nbsp; &nbsp; &nbsp; java -jar <path-to-jar-file> <path-to-config-file> <path-to-stat-file> <path-to-object-file> <br>
Run the script &nbsp; &nbsp; &nbsp; python test zip.py <path-to-zip-file> to check your submission. <br><br>
