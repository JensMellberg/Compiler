.global _start
.data
buf: .skip 1024

.text
_start:
        call main
        movq %rax, %rdi
        movq $60, %rax
        syscall
main:
        pushq %rbp
        movq %rsp, %rbp
        subq $0, %rsp
        movq $0, %rax
        movq $3, %rax
        pushq %rax
        movq $2, %rax
        pushq %rax
        movq $1, %rax
        pushq %rax
        movq %rax, -8(%rbp)
        movq -8(%rbp), %rax
        movq 16(%rax), %rax
        pushq %rax
        call print
        addq $8, %rsp
        movq $0, %rax
        movq %rbp, %rsp
        popq %rbp
        ret

# Procedure to read number from stdin.
# C signature: long long int read(void)
read:
        pushq %rbp
        movq %rsp, %rbp
        ### R9  = sign
        movq $1, %r9            # sign <- 1
        ### R10 = sum
        movq $0, %r10           # sum <- 0
skip_ws: # skip any leading whitespace
        movq $0, %rdi
        movq $buf, %rsi
        movq $1, %rdx
        movq $0, %rax
        syscall                 # get one char: sys_read(0, buf, 1)
        cmpq $0, %rax
        jle atoi_done           # nchar <= 0
        movb (%rsi), %cl        # c <- current char
        cmp $32, %cl
        je skip_ws              # c == space
        cmp $13, %cl
        je skip_ws              # c == CR
        cmp $10, %cl
        je skip_ws              # c == NL
        cmp $9, %cl
        je skip_ws              # c == tab
        cmp $45, %cl            # check if negative
        jne atoi_loop
        movq $-1, %r9           # sign <- -1
        movq $0, %rdi
        movq $buf, %rsi
        movq $1, %rdx
        movq $0, %rax
        syscall                 # get one char: sys_read(0, buf, 1)
atoi_loop:
        cmpq $0, %rax           # while (nchar > 0)
        jle atoi_done           # leave loop if nchar <= 0
        movzbq (%rsi), %rcx     # move byte, zero extend to quad-word
        cmpq $0x30, %rcx        # test if < '0'
        jl atoi_done            # character is not numeric
        cmpq $0x39, %rcx        # test if > '9'
        jg atoi_done            # character is not numeric
        imulq $10, %r10         # multiply sum by 10
        subq $0x30, %rcx        # value of character
        addq %rcx, %r10         # add to sum
        movq $0, %rdi
        movq $buf, %rsi
        movq $1, %rdx
        movq $0, %rax
        syscall                 # get one char: sys_read(0, buf, 1)
        jmp atoi_loop           # loop back
atoi_done:
        imulq %r9, %r10         # sum *= sign
        movq %r10, %rax         # put result value in RAX
        popq %rbp
        ret

# Procedure to print number to stdout.
# C signature: void print(long int)
print:
        pushq %rbp
        movq %rsp, %rbp
        ### Convert integer to string (itoa).
        movq 16(%rbp), %rax
        movq $(buf+1023), %rsi  # RSI = write pointer (starts at end of buffer)
        movb $0x0A, (%rsi)      # insert newline
        movq $1, %rcx           # RCX = string length
        cmpq $0, %rax
        jge itoa_loop
        negq %rax               # negate to make RAX positive
itoa_loop:                      # do.. while (at least one iteration)
        movq $10, %rdi
        movq $0, %rdx
        idivq %rdi              # divide RDX:RAX by 10
        addb $0x30, %dl         # remainder + '0'
        decq %rsi               # move string pointer
        movb %dl, (%rsi)
        incq %rcx               # increment string length
        cmpq $0, %rax
        jg itoa_loop            # produce more digits
itoa_done:
        movq 16(%rbp), %rax
        cmpq $0, %rax
        jge print_end
        decq %rsi
        incq %rcx
        movb $0x2D, (%rsi)
print_end:
        movq $1, %rdi
        movq %rcx, %rdx
        movq $1, %rax
        syscall
        popq %rbp
        ret

print_string:
        pushq %rbp
        movq %rsp, %rbp
        movq $1, %rdi
        movq 16(%rbp), %rsi
        movq 24(%rbp), %rdx
        movq $1, %rax
        syscall
        popq %rbp
        ret
