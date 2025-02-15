	.data
a:
	12
	.text
main:
    load %x0, $a, %x3       //x3=a (unoperated)
    load %x0, $a, %x4       //x4=a
    addi %x0, 0, %x7        //x7 = temp = 0
loop:
    divi %x4, 10, %x4       //x4 = quotient
    add %x31, %x7, %x6      //x6 = temp + remainder
    beq %x4, %x0, end
    muli %x6, 10, %x7       // x7 = x6 * 10
    jmp loop
end:
    beq %x6, %x3, palindrome    //palindrome
    subi %x0, 1, %x10           // not palindrome
    end
palindrome:
    addi %x0, 1, %x10
    end