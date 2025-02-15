	.data
a:
	70
	80
	40
	20
	-10
n:
	5
	.text
main:
    load %x0, $a, %x11      //x11 = a
    load %x0, $n, %x12      //x12 = n
    subi %x0, 1, %x3        //x3 = -1
    subi %x12, 1, %x4       //x4 = n-1
    jmp outer
    end
outer:
    addi %x3, 1, %x3        //x3++
    subi %x0, 1, %x5        //x5 = -1
    subi %x12, 2, %x15      //x15 = n-2
    subi %x15, %x3, %x6     //x6=n-i-2
    beq %x3, %x4, end
    jmp inner               //jmp inner
inner:
    addi %x5, 1, %x5        //x5++
    load %x5, $a, %x7       // x7 = arr[j]
    addi %x5, 1, %x8        // x8 = j+1
    load %x8, $a, %x9       // x9 = arr[j+1]
    bgt %x9, %x7, condition //x9>x7
    blt %x5, %x6, inner     //x5 < n-1 then continue
    jmp outer
condition:
    store %x9, $a, %x5    //   store this base offsetRegister
    store %x7, $a, %x8    // 
    blt %x5, %x6, inner    // j<n-1 tak outer
    jmp outer
end:
    end