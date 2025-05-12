# 📦 Project Directory Structure

```plaintext
📦 **Computer-Architecture-Lab**
├── 📂 Lab 1
│   ├── 📑 CS23BT071_assignment1_partA.pdf
│   ├── 📑 Lab1_handout.pdf
│   ├── 📂 supporting_files
│   │   ├── 🗜️ CS23BT071_assignment1.zip
│   │   ├── ⚙️ descending.asm
│   │   ├── 📄 emulator.jar
│   │   ├── 🐍 evaluate.py
│   │   ├── ⚙️ even-odd.asm
│   │   ├── ⚙️ fibonacci.asm
│   │   ├── ⚙️ palindrome.asm
│   │   ├── ⚙️ prime.asm
│   │   ├── 📂 program_templates
│   │   │   ├── ⚙️ descending_template.asm
│   │   │   ├── ⚙️ even-odd_template.asm
│   │   │   ├── ⚙️ fibonancci_template.asm
│   │   │   ├── ⚙️ palindrome_template.asm
│   │   │   └── ⚙️ prime_template.asm
│   │   ├── 📂 test_cases
│   │   │   ├── 📌 descending_1.expected
│   │   │   ├── 📥 descending_1.input
│   │   │   ├── 📌 even-odd_1.expected
│   │   │   ├── 📥 even-odd_1.input
│   │   │   ├── 📌 fibonacci_1.expected
│   │   │   ├── 📥 fibonacci_1.input
│   │   │   ├── 📌 palindrome_1.expected
│   │   │   ├── 📥 palindrome_1.input
│   │   │   ├── 📌 prime_1.expected
│   │   │   └── 📥 prime_1.input
│   │   ├── 🐍 test_each.py
│   │   └── 🐍 test_zip.py
│   └── 📂 test_cases_for_evaluation
│       ├── 📌 descending_1.expected
│       ├── 📥 descending_1.input
│       ├── 📌 descending_2.expected
│       ├── 📥 descending_2.input
│       ├── 📌 even-odd_1.expected
│       ├── 📥 even-odd_1.input
│       ├── 📌 even-odd_2.expected
│       ├── 📥 even-odd_2.input
│       ├── 📌 fibonacci_1.expected
│       ├── 📥 fibonacci_1.input
│       ├── 📌 fibonacci_2.expected
│       ├── 📥 fibonacci_2.input
│       ├── 📌 palindrome_1.expected
│       ├── 📥 palindrome_1.input
│       ├── 📌 palindrome_2.expected
│       ├── 📥 palindrome_2.input
│       ├── 📌 prime_1.expected
│       ├── 📥 prime_1.input
│       ├── 📌 prime_2.expected
│       └── 📥 prime_2.input
│
│
├── 📂 Lab 2
│   ├── 📑 CS311_Lab2.pdf
│   └── 📂 supporting_files
│       ├── 🗜️ CS23BT071_assignment2.zip
│       ├── 📜 build.xml
│       ├── 📂 src
│       │   ├── 📂 configuration
│       │   │   ├── ☕ Configuration.java
│       │   │   ├── 📜 config.xml
│       │   │   ├── ⚙️ fibonancci.asm
│       │   │   ├── 📄 output.obj
│       │   │   └── 📄 test.stat
│       │   ├── 📂 generic
│       │   │   ├── ☕ Instruction.java
│       │   │   ├── ☕ Misc.java
│       │   │   ├── ☕ Operand.java
│       │   │   ├── ☕ ParsedProgram.java
│       │   │   ├── ☕ Simulator.java
│       │   │   └── ☕ Statistics.java
│       │   └── 📂 main
│       │       └── ☕ Main.java
│       ├── 📂 test_cases
│       │   ├── ⚙️ 1.asm
│       │   ├── 📤 1.out
│       │   ├── ⚙️ 2.asm
│       │   ├── 📤 2.out
│       │   ├── ⚙️ 3.asm
│       │   └── 📤 3.out
│       └── 🐍 test_zip.py
│
│
├── 📂 Lab 3
│   ├── 📑 Computer Architecture Statistics of test case.pdf
│   ├── 📑 LAB3.pdf
│   ├── 📝 stat.txt
│   ├── 📝 stat1.txt
│   ├── 📝 stat2.txt
│   ├── 📝 stat3.txt
│   ├── 📝 stat4.txt
│   ├── 📝 stat5.txt
│   └── 📂 supporting_files
│       ├── 🗜️ CS23BT071_assignment3.zip
│       ├── 📜 build.xml
│       ├── 📂 src
│       │   ├── 📂 configuration
│       │   │   ├── ☕ Configuration.java
│       │   │   └── 📜 config.xml
│       │   ├── 📂 generic
│       │   │   ├── ☕ Instruction.java
│       │   │   ├── ☕ Misc.java
│       │   │   ├── ☕ Operand.java
│       │   │   ├── ☕ Simulator.java
│       │   │   └── ☕ Statistics.java
│       │   ├── 📂 main
│       │   │   └── ☕ Main.java
│       │   └── 📂 processor
│       │       ├── ☕ Clock.java
│       │       ├── ☕ Processor.java
│       │       ├── 📂 memorysystem
│       │       │   └── ☕ MainMemory.java
│       │       └── 📂 pipeline
│       │           ├── ☕ EX_IF_LatchType.java
│       │           ├── ☕ EX_MA_LatchType.java
│       │           ├── ☕ Execute.java
│       │           ├── ☕ IF_EnableLatchType.java
│       │           ├── ☕ IF_OF_LatchType.java
│       │           ├── ☕ InstructionFetch.java
│       │           ├── ☕ MA_RW_LatchType.java
│       │           ├── ☕ MemoryAccess.java
│       │           ├── ☕ OF_EX_LatchType.java
│       │           ├── ☕ OperandFetch.java
│       │           ├── ☕ RegisterFile.java
│       │           └── ☕ RegisterWrite.java
│       ├── 📂 test_cases
│       │   ├── ⚙️ descending.asm
│       │   ├── 🔒 descending.endsystemstate
│       │   ├── 📌 descending.expected
│       │   ├── 📤 descending.out
│       │   ├── ⚙️ evenorodd.asm
│       │   ├── 🔒 evenorodd.endsystemstate
│       │   ├── 📌 evenorodd.expected
│       │   ├── 📤 evenorodd.out
│       │   ├── ⚙️ fibonacci.asm
│       │   ├── 🔒 fibonacci.endsystemstate
│       │   ├── 📌 fibonacci.expected
│       │   ├── 📤 fibonacci.out
│       │   ├── ⚙️ palindrome.asm
│       │   ├── 🔒 palindrome.endsystemstate
│       │   ├── 📌 palindrome.expected
│       │   ├── 📤 palindrome.out
│       │   ├── ⚙️ prime.asm
│       │   ├── 🔒 prime.endsystemstate
│       │   ├── 📌 prime.expected
│       │   └── 📤 prime.out
│       └── 🐍 test_zip.py
│
│
├── 📂 Lab 4
│   ├── 📑 LabA4-1.pdf
│   └── 📂 supporting_files
│       ├── 🗜️ CS23BT071_assignment4.zip
│       ├── 📜 build.xml
│       ├── 📂 src
│       │   ├── 📂 configuration
│       │   │   ├── ☕ Configuration.java
│       │   │   └── 📜 config.xml
│       │   ├── 📂 generic
│       │   │   ├── ☕ Instruction.java
│       │   │   ├── ☕ Misc.java
│       │   │   ├── ☕ Operand.java
│       │   │   ├── ☕ Simulator.java
│       │   │   └── ☕ Statistics.java
│       │   ├── 📂 main
│       │   │   └── ☕ Main.java
│       │   └── 📂 processor
│       │       ├── ☕ Clock.java
│       │       ├── ☕ Processor.java
│       │       ├── 📂 memorysystem
│       │       │   └── ☕ MainMemory.java
│       │       └── 📂 pipeline
│       │           ├── ☕ Branch_Hazard.java
│       │           ├── ☕ Data_Hazard.java
│       │           ├── ☕ EX_IF_LatchType.java
│       │           ├── ☕ EX_MA_LatchType.java
│       │           ├── ☕ Execute.java
│       │           ├── ☕ IF_EnableLatchType.java
│       │           ├── ☕ IF_OF_LatchType.java
│       │           ├── ☕ InstructionFetch.java
│       │           ├── ☕ MA_RW_LatchType.java
│       │           ├── ☕ MemoryAccess.java
│       │           ├── ☕ OF_EX_LatchType.java
│       │           ├── ☕ OperandFetch.java
│       │           ├── ☕ RegisterFile.java
│       │           └── ☕ RegisterWrite.java
│       ├── 📂 test_cases
│       │   ├── ⚙️ descending.asm
│       │   ├── 🔒 descending.endsystemstate
│       │   ├── 📌 descending.expected
│       │   ├── 📤 descending.out
│       │   ├── ⚙️ evenorodd.asm
│       │   ├── 🔒 evenorodd.endsystemstate
│       │   ├── 📌 evenorodd.expected
│       │   ├── 📤 evenorodd.out
│       │   ├── ⚙️ fibonacci.asm
│       │   ├── 🔒 fibonacci.endsystemstate
│       │   ├── 📌 fibonacci.expected
│       │   ├── 📤 fibonacci.out
│       │   ├── ⚙️ palindrome.asm
│       │   ├── 🔒 palindrome.endsystemstate
│       │   ├── 📌 palindrome.expected
│       │   ├── 📤 palindrome.out
│       │   ├── ⚙️ prime.asm
│       │   ├── 🔒 prime.endsystemstate
│       │   ├── 📌 prime.expected
│       │   └── 📤 prime.out
│       └── 🐍 test_zip.py
│
│
├── 📂 Lab 5
│   ├── 📑 Assignment 5.pdf
│   └── 📂 supporting_files
│       ├── 🗜️ CS23BT071_assignment5.zip
│       ├── 📜 build.xml
│       ├── 📂 src
│       │   ├── 📂 configuration
│       │   │   ├── ☕ Configuration.java
│       │   │   └── 📜 config.xml
│       │   ├── 📂 generic
│       │   │   ├── ☕ Element.java
│       │   │   ├── ☕ Event.java
│       │   │   ├── ☕ EventQueue.java
│       │   │   ├── ☕ ExecutionCompleteEvent.java
│       │   │   ├── ☕ Instruction.java
│       │   │   ├── ☕ MemoryReadEvent.java
│       │   │   ├── ☕ MemoryResponseEvent.java
│       │   │   ├── ☕ MemoryWriteEvent.java
│       │   │   ├── ☕ Misc.java
│       │   │   ├── ☕ Operand.java
│       │   │   ├── ☕ Simulator.java
│       │   │   └── ☕ Statistics.java
│       │   ├── 📂 main
│       │   │   └── ☕ Main.java
│       │   └── 📂 processor
│       │       ├── ☕ Clock.java
│       │       ├── ☕ Processor.java
│       │       ├── 📂 memorysystem
│       │       │   └── ☕ MainMemory.java
│       │       └── 📂 pipeline
│       │           ├── ☕ Branch_Hazard.java
│       │           ├── ☕ Data_Hazard.java
│       │           ├── ☕ EX_IF_LatchType.java
│       │           ├── ☕ EX_MA_LatchType.java
│       │           ├── ☕ Execute.java
│       │           ├── ☕ IF_EnableLatchType.java
│       │           ├── ☕ IF_OF_LatchType.java
│       │           ├── ☕ InstructionFetch.java
│       │           ├── ☕ MA_RW_LatchType.java
│       │           ├── ☕ MemoryAccess.java
│       │           ├── ☕ OF_EX_LatchType.java
│       │           ├── ☕ OperandFetch.java
│       │           ├── ☕ RegisterFile.java
│       │           └── ☕ RegisterWrite.java
│       ├── 📂 test_cases
│       │   ├── ⚙️ descending.asm
│       │   ├── 🔒 descending.endsystemstate
│       │   ├── 📌 descending.expected
│       │   ├── 📤 descending.out
│       │   ├── ⚙️ evenorodd.asm
│       │   ├── 🔒 evenorodd.endsystemstate
│       │   ├── 📌 evenorodd.expected
│       │   ├── 📤 evenorodd.out
│       │   ├── ⚙️ fibonacci.asm
│       │   ├── 🔒 fibonacci.endsystemstate
│       │   ├── 📌 fibonacci.expected
│       │   ├── 📤 fibonacci.out
│       │   ├── ⚙️ palindrome.asm
│       │   ├── 🔒 palindrome.endsystemstate
│       │   ├── 📌 palindrome.expected
│       │   ├── 📤 palindrome.out
│       │   ├── ⚙️ prime.asm
│       │   ├── 🔒 prime.endsystemstate
│       │   ├── 📌 prime.expected
│       │   └── 📤 prime.out
│       └── 🐍 test_zip.py
│
│
├── 📂 Lab 6
│   ├── 📑 CS23BT071_lab6_report.pdf
│   ├── 📑 LabA6.pdf
│   ├── ⚙️ ap.asm
│   ├── 📤 ap.out
│   ├── ⚙️ ascending.asm
│   ├── 📤 ascending.out
│   └── 📂 supporting_files
│       ├── 🗜️ CS23BT071_assignment6.zip
│       ├── 📜 build.xml
│       ├── 📂 src
│       │   ├── 📂 configuration
│       │   │   ├── ☕ Configuration.java
│       │   │   └── 📜 config.xml
│       │   ├── 📂 generic
│       │   │   ├── ☕ Element.java
│       │   │   ├── ☕ Event.java
│       │   │   ├── ☕ EventQueue.java
│       │   │   ├── ☕ ExecutionCompleteEvent.java
│       │   │   ├── ☕ Instruction.java
│       │   │   ├── ☕ MemoryReadEvent.java
│       │   │   ├── ☕ MemoryResponseEvent.java
│       │   │   ├── ☕ MemoryWriteEvent.java
│       │   │   ├── ☕ Misc.java
│       │   │   ├── ☕ Operand.java
│       │   │   ├── ☕ Simulator.java
│       │   │   └── ☕ Statistics.java
│       │   ├── 📂 main
│       │   │   └── ☕ Main.java
│       │   └── 📂 processor
│       │       ├── ☕ Clock.java
│       │       ├── ☕ Processor.java
│       │       ├── 📂 memorysystem
│       │       │   ├── ☕ Cache.java
│       │       │   ├── ☕ CacheLine.java
│       │       │   └── ☕ MainMemory.java
│       │       └── 📂 pipeline
│       │           ├── ☕ EX_IF_LatchType.java
│       │           ├── ☕ EX_MA_LatchType.java
│       │           ├── ☕ Execute.java
│       │           ├── ☕ IF_EnableLatchType.java
│       │           ├── ☕ IF_OF_LatchType.java
│       │           ├── ☕ InstructionFetch.java
│       │           ├── ☕ MA_RW_LatchType.java
│       │           ├── ☕ MemoryAccess.java
│       │           ├── ☕ OF_EX_LatchType.java
│       │           ├── ☕ OperandFetch.java
│       │           ├── ☕ RegisterFile.java
│       │           └── ☕ RegisterWrite.java
│       ├── 📂 test_cases
│       │   ├── ⚙️ descending.asm
│       │   ├── 🔒 descending.endsystemstate
│       │   ├── 📌 descending.expected
│       │   ├── 📤 descending.out
│       │   ├── ⚙️ evenorodd.asm
│       │   ├── 🔒 evenorodd.endsystemstate
│       │   ├── 📌 evenorodd.expected
│       │   ├── 📤 evenorodd.out
│       │   ├── ⚙️ fibonacci.asm
│       │   ├── 🔒 fibonacci.endsystemstate
│       │   ├── 📌 fibonacci.expected
│       │   ├── 📤 fibonacci.out
│       │   ├── ⚙️ palindrome.asm
│       │   ├── 🔒 palindrome.endsystemstate
│       │   ├── 📌 palindrome.expected
│       │   ├── 📤 palindrome.out
│       │   ├── ⚙️ prime.asm
│       │   ├── 🔒 prime.endsystemstate
│       │   ├── 📌 prime.expected
│       │   └── 📤 prime.out
│       └── 🐍 test_zip.py
│
│
├── 📂 MY OUTPUTS
│   ├── 📂 Output from Lab 2
│   │   ├── 📄 assembler_for_lab1_asm.sh
│   │   ├── ⚙️ descending.asm
│   │   ├── 📤 descending.out
│   │   ├── ⚙️ even-odd.asm
│   │   ├── 📤 evenorodd.out
│   │   ├── 📝 evenorodd.txt
│   │   ├── ⚙️ fibonacci.asm
│   │   ├── 📤 fibonacci.out
│   │   ├── ⚙️ palindrome.asm
│   │   ├── 📤 palindrome.out
│   │   ├── ⚙️ prime.asm
│   │   └── 📤 prime.out
│   ├── 📂 Statistics from Lab 3
│   │   ├── 📤 descending.out
│   │   ├── 📝 descending.txt
│   │   ├── 📝 fibonacci.txt
│   │   ├── 📄 fix_java_ant.sh
│   │   ├── 📝 palindrome.txt
│   │   ├── 📝 prime.txt
│   │   └── 📄 run_simulator.sh
│   ├── 📂 Statistics from Lab 4
│   │   ├── 📂 100 Final Statistics
│   │   │   ├── 📤 descending.out
│   │   │   ├── 📝 descending.txt
│   │   │   ├── 📝 evenorodd.txt
│   │   │   ├── 📝 fibonacci.txt
│   │   │   ├── 📝 palindrome.txt
│   │   │   ├── 📝 prime.txt
│   │   │   ├── 📝 temp1.txt
│   │   │   ├── 📝 temp2.txt
│   │   │   ├── 📝 temp3.txt
│   │   │   ├── 📝 temp4.txt
│   │   │   └── 📝 temp5.txt
│   │   ├── 📂 FINAL STATISTICS
│   │   │   ├── 📝 descending.txt
│   │   │   ├── 📝 evenorodd.txt
│   │   │   ├── 📝 fibonacci.txt
│   │   │   ├── 📝 palindrome.txt
│   │   │   ├── 📝 prime.txt
│   │   │   ├── 📝 temp1.txt
│   │   │   ├── 📝 temp2.txt
│   │   │   ├── 📝 temp3.txt
│   │   │   ├── 📝 temp4.txt
│   │   │   └── 📝 temp5.txt
│   │   ├── 📂 MY CODE STATISTICS
│   │   │   ├── 📝 descending.txt
│   │   │   ├── 📝 evenorodd.txt
│   │   │   ├── 📝 fibonacci.txt
│   │   │   ├── 📝 palindrome.txt
│   │   │   ├── 📝 prime.txt
│   │   │   ├── 📝 temp.txt
│   │   │   ├── 📝 temp10.txt
│   │   │   ├── ⚙️ temporary.asm
│   │   │   ├── 📤 temporary.out
│   │   │   └── 📝 temporary.txt
│   │   ├── 📂 RESEARCH STATISTICS
│   │   │   ├── 📝 descending.txt
│   │   │   ├── 📝 evenorodd.txt
│   │   │   ├── 📝 fibonacci.txt
│   │   │   ├── 📝 palindrome.txt
│   │   │   ├── 📝 prime.txt
│   │   │   ├── 📝 temp1.txt
│   │   │   ├── 📝 temp2.txt
│   │   │   ├── 📝 temp3.txt
│   │   │   ├── 📝 temp4.txt
│   │   │   └── 📝 temp5.txt
│   │   ├── 📄 run.sh
│   │   └── 📂 temporary
│   │       ├── 🗜️ CS23BT071_assignment4.zip
│   │       ├── 🗜️ CSS_temp.zip
│   │       ├── 📝 descending.txt
│   │       ├── 📝 prime.txt
│   │       ├── 📝 stats.txt
│   │       ├── 📝 stats1.txt
│   │       ├── 📝 stats2.txt
│   │       ├── 📝 stats3.txt
│   │       ├── 📝 stats4.txt
│   │       ├── 📝 stats5.txt
│   │       ├── 📝 statsfinal.txt
│   │       ├── 📝 temp.txt
│   │       ├── 📝 temp1.txt
│   │       ├── 📝 temp10.txt
│   │       └── 📝 temp5.txt
│   └── 📂 Statistics from Lab 5
│       ├── 📂 Benchmarks
│       │   ├── 📤 descending.out
│       │   ├── 📤 evenorodd.out
│       │   ├── 📤 fibonacci.out
│       │   ├── 📤 palindrome.out
│       │   └── 📤 prime.out
│       ├── 📂 Benchmarks stat
│       │   ├── 📝 descending_bench_stat.txt
│       │   ├── 📝 even_bench_stat.txt
│       │   ├── 📝 fibonacci_bench_stat.txt
│       │   ├── 📝 palindrome_bench_stat.txt
│       │   └── 📝 prime_bench_stat.txt
│       ├── 📂 Testcasestats
│       │   ├── 📝 descending_stat.txt
│       │   ├── 📝 evenorodd_stat.txt
│       │   ├── 📝 fibonacci_stat.txt
│       │   ├── 📝 palindrome_stat.txt
│       │   └── 📝 prime_stat.txt
│       └── 📂 test_cases
│           ├── ⚙️ descending.asm
│           ├── 🔒 descending.endsystemstate
│           ├── 📌 descending.expected
│           ├── 📤 descending.out
│           ├── ⚙️ evenorodd.asm
│           ├── 🔒 evenorodd.endsystemstate
│           ├── 📌 evenorodd.expected
│           ├── 📤 evenorodd.out
│           ├── ⚙️ fibonacci.asm
│           ├── 🔒 fibonacci.endsystemstate
│           ├── 📌 fibonacci.expected
│           ├── 📤 fibonacci.out
│           ├── ⚙️ palindrome.asm
│           ├── 🔒 palindrome.endsystemstate
│           ├── 📌 palindrome.expected
│           ├── 📤 palindrome.out
│           ├── ⚙️ prime.asm
│           ├── 🔒 prime.endsystemstate
│           ├── 📌 prime.expected
│           └── 📤 prime.out
│
│
├── 📖 README.md
├── 📖 directory_tree.md
└── 📑 toyrisc_isa_specification.pdf
```