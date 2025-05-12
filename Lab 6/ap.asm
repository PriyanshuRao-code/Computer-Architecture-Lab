    .data
n:
    5
a:
    2
d:
    3
    .text
main:
    load %x0, $n, %x3
    load %x0, $a, %x4
    load %x0, $d, %x5
    addi %x0, 0, %x6
    addi %x0, 65535, %x7
loop:
    store %x4, 0, %x7
    add %x4, %x5, %x4
    subi %x7, 1, %x7
    addi %x6, 1, %x6
    beq %x6, %x3, stop
    jmp loop
stop:
    end