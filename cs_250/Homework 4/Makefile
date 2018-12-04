CC = gcc
CFLAGS = -g -Wall
INCLUDE1=DieWithError.c

all: BroadcastSenderv6 BroadcastReceiverv6

BroadcastSenderv6: BroadcastSenderv6.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

BroadcastReceiverv6: BroadcastReceiverv6.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

clean:
	-rm -f *.o *~ *core*
	-rm -rf *.o.dSYM
