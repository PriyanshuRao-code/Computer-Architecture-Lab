# Computer-Architecture-Lab

The given below are some commands used. But to be sure about the format and extensive detailing read the Lab handouts present in every lab. <br>
ToyRISC specifications are available in Lab 1. <br>
(Tip: If you are creating jar file and not running as expected i.e. eg in Lab 3, use `ant clean` (May help as it helped me in the lab) and then create again.) <br><br>

## Lab 1 - All the asm files and analysis of compilers and ISA <br>

To test your program, use the given Java application emulator.jar. The command is `java -jar <path-to-emulator.jar><path-to-assembly-file><starting-address><ending-address>` <br>
One test case for each program is given. To evaluate, the command is `python test_each.py <path-to-assembly-file>` <br>
To test your zip archive, the command is `python test_zip.py <path-to-zip-archive>` <br><br>

## Lab 2 - Assembler for the ToyRISC ISA. <br>

`ant make-jar` (For making jar file) (A jar file is created at the location jars/assembler.jar) <br>
To run your program, run this command on the terminal: `java -Xmx1g -jar <path-to-jar-file> <path-to-assembly-program> <path-to-object-file>` <br>
Run the script: `python test zip.py <path-to-zip-file>` in the same folder that contains build.xml, test cases, and test zip.py. <br><br>

## Lab 3 - Single Cycle Processor. <br>

`ant make-jar` (For making jar file) <br>
For printing the statistics: `java -jar <path-to-jar-file> <path-to-config-file> <path-to-stat-file> <path-to-object-file>` <br>
Run the script: `python test zip.py <path-to-zip-file>` to check your submission. <br><br>

## 📂 Project Structure

👉 View the full directory structure here: [directory_tree.md](directory_tree.md)
