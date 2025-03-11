    .data
a:
    4
    .text
main:
    load %x0, $a, %x1
    addi %x0, 1, %x2
    and %x1, %x2, %x3
    beq %x0, %x3, even
    addi %x0, 1, %x10
    end
even:
    subi %x0, 1, %x10
    end