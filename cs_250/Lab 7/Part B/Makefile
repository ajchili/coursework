CC = gcc
CFLAGS = -g -Wall
SCTPFLAG = -lsctp
INCLUDE1=DieWithError.c

all: EchoClientMto1SCTPv6 EchoServerMto1SCTPv6

EchoClientMto1SCTPv6: EchoClientMto1SCTPv6.c
	$(CC) $(CFLAGS) $@.c $(INCLUDE1) -o $@.o $(SCTPFLAG)

EchoServerMto1SCTPv6: EchoServerMto1SCTPv6.c
	$(CC) $(CFLAGS) $@.c $(INCLUDE1) -o $@.o $(SCTPFLAG)

clean:
	-rm -f *.o *~ *core*
