CC = gcc
CFLAGS = -g -Wall
INCLUDE1=DieWithError.c
INCLUDE2=DieWithError.c HandleClient.c

all: EchoClient1to1SCTP EchoServer1to1SCTP

EchoClient1to1SCTP: EchoClient1to1SCTP.c
	$(CC) $(CFLAGS) $(INCLUDE1) -o $@.o $<

EchoServer1to1SCTP: EchoServer1to1SCTP.c
	$(CC) $(CFLAGS) $(INCLUDE2) -o $@.o $<

clean:
	-rm -f *.o *~ *core* 
