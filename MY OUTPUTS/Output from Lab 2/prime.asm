	.data
a:
	11
	.text
main:
    load %x0, $a, %x3       //x3 = num
    addi %x0, 2, %x4        //x4 = 2
    jmp loop
loop:
    beq %x3, %x4, prime         // prime
    div %x3, %x4, %x5           //x5 = quotient
    beq %x31, %x0, notprime     //notprime
    addi %x4, 1, %x4            //x4++
    jmp loop    
notprime:
    subi %x0, 1, %x10
    end
prime:
    addi %x0, 1, %x10
    end
