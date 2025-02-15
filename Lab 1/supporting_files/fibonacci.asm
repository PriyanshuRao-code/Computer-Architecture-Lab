	.data
n:
	10
	.text
main:
    subi %x0, 1, %x3        //x3=0 (i)
    addi %x0, 0, %x4        //x4=0 (t1)
    addi %x0, 1, %x5        //x5=1  (t2)
    load %x0, $n, %x6       //x6=n
    store %x4, 0, %x10      // storing into x10
    subi %x0, 1, %x11       // x11=-1
    store %x5, 65535, %x11     // storing  into x10 -1
loop:
    addi %x3, 1, %x3        //x3++
    add %x4, %x5, %x7       //next-term = t1 + t2
    subi %x11, 1, %x11      //x11--
    store %x7, 65535, %x11
    add %x5, %x0, %x4       // x4=x5 (t1=t2)
    add %x7, %x0, %x5       // x5 = x7 (t2 = next-terms)
    blt %x3, %x6, loop
    end