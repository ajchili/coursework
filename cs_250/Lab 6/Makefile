CC = gcc
CFLAGS = -g -Wall
INCLUDE1=DieWithError.c
INCLUDE2=DieWithError.c HandleClientTCP.c

all: EchoClientTCPv6 EchoServerTCPv6

EchoClientTCPv6: EchoClientTCPv6.c
	$(CC) $(CFLAGS) $(INCLUDE1) -o $@.o $<

EchoServerTCPv6: EchoServerTCPv6.c
	$(CC) $(CFLAGS) $(INCLUDE2) -o $@.o $<

clean:
	-rm -f *.o *~ *core* 
