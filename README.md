# Computer Architecture Lab

This repository contains my implementations and notes for various labs conducted as part of the **Computer Architecture** course. Each lab focuses on a specific concept in modern processor design and simulation.  

📌 **Note:**  
For Lab 6, I made significant changes compared to Lab 5 due to recurring errors. I restarted the lab implementation from scratch with help from my friends *Harshit Paras Babariya* and *Pakhare Yash Sunil*. If you'd like to continue from Lab 5's implementation, I suggest reviewing and comparing the files of Lab 5 and Lab 6 to trace the differences. I couldn't continue directly due to time constraints.

💡 **Tip:**  
If you're creating a `.jar` file and it doesn't run as expected (for example, in Lab 3), try running `ant clean` before rebuilding. It resolved issues for me during my experiments.

> **Each lab folder contains a README and handouts for detailed instructions. Please refer to them before execution.**

---


## Lab 1 – Assembly Files, Compiler Analysis, and ISA Comparison

This lab involves writing ToyRISC assembly programs, analyzing compiler behavior, and comparing Instruction Set Architectures (ISAs).

### 🔧 How to Run:

Use the provided `emulator.jar` Java application to run your `.asm` programs:

```bash
java -jar <path-to-emulator.jar> <path-to-assembly-file> <starting-address> <ending-address>
```

### 🧪 Testing Your Programs
Each assembly program includes one test case. Use the following commands to evaluate:
- To test an individual assembly file:
```bash
python test_each.py <path-to-assembly-file>
```
- To test your full ZIP archive:
```bash
 python test_zip.py <path-to-zip-archive>
```

---

## Lab 2 - Assembler for the ToyRISC ISA.

Developed an assembler that converts high-level ToyRISC instructions into binary machine code.  
The generated binary file serves as input for subsequent labs and forms the backbone of instruction execution in processor simulations.

### ⚙️ How to Build

Use the following command to compile and generate the JAR file:

```bash
ant make-jar
```

📁 The generated JAR file will be located at:  
`jars/assembler.jar`

### 🚀 How to Run

To run your assembler on a `.asm` file, use the following command:

```bash
java -Xmx1g -jar <path-to-jar-file> <path-to-assembly-program> <path-to-object-file>
```

### 🧪 How to Test

Run the following script to test your zipped submission:

```bash
python test_zip.py <path-to-zip-file>
```

📌 *Make sure this script is executed in the same folder that contains `build.xml`, test cases, and `test_zip.py`.*

---


## Lab 3 - Single Cycle Processor.

Implemented a single-cycle processor that takes the binary file (generated from Lab 2) as input and simulates instruction execution in a single clock cycle per instruction.  
Post execution, the **register file** and **memory contents** are verified against expected outputs to ensure correctness of execution.


➡️ Refer to the [Usage Instructions](#usage-instructions-applicable-for-labs-3-6) for build, run, and test commands.

---

## Lab 4 - Pipelined processor.

Implemented a pipelined processor and introduced **stalls** to handle **data hazards** and **branch hazards**, ensuring smooth execution of the instruction pipeline.


➡️ Refer to the [Usage Instructions](#usage-instructions-applicable-for-labs-3-6) for build, run, and test commands.

---

## Lab 5 - Discrete Event Simulator.

Developed a discrete event simulator using an `Event` class from `Event.java` to mimic real-world latency in the following stages:
- **Instruction Fetch** (`InstructionFetch.java`)
- **Execution** (`Execute.java`)
- **Memory Access** (`MemoryAccess.java`)

Other files were appropriately modified to accommodate these latencies and maintain smooth program flow.


➡️ Refer to the [Usage Instructions](#usage-instructions-applicable-for-labs-3-6) for build, run, and test commands.

---

## Lab 6 - Cache Integration.

Introduced cache simulation through `Cache.java` and `CacheLine.java`, located in `supporting_files/src/processor/memorysystem`.

Key highlights:
- Significantly improves performance over Lab 5 by reducing memory access latency.
- Fully rewritten implementation due to issues in the previous lab.

📁 **To test different cache sizes and configurations**, edit:`supporting_files/src/configuration/config.xml`. 

➡️ Refer to the [Usage Instructions](#usage-instructions-applicable-for-labs-3-6) for build, run, and test commands.


---

## 📌 Usage Instructions (Applicable for Labs 3-6)

The following instructions are common for **Lab 3 – Single Cycle Processor**, **Lab 4 – Pipelined Processor**, **Lab 5 – Discrete Event Simulator**, and **Lab 6 – Cache Integration**.

### 🛠️ Build the JAR File

Use the following command to compile your Java code and generate the JAR:

```bash
ant make-jar
```

📍 This will create the JAR file at:  `jars/processor.jar` 

---

### 🚀 Run the Simulator

To run the processor simulator and print the statistics:

```bash
java -jar <path-to-jar-file> <path-to-config-file> <path-to-stat-file> <path-to-object-file>
```

📝 Example:

```bash
 java -jar jars\simulator.jar src\configuration\config.xml stat.txt test_cases\descending.out
```

---

### 🧪 Test the Submission

To test your zipped submission:

```bash
python test_zip.py <path-to-zip-file>
```

📌 Make sure this script is executed in the **same folder** where `build.xml`, `test_zip.py`, and test cases are located.

---

## 📂 Project Structure

👉 View the full directory structure here: [directory_tree.md](directory_tree.md)
