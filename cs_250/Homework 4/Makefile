CC = gcc
CFLAGS = -g -Wall
INCLUDE1=DieWithError.c

all: BroadcastSender BroadcastReceiver

BroadcastSender: BroadcastSender.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

BroadcastReceiver: BroadcastReceiver.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

clean:
	-rm -f *.o *~ *core*
	-rm -rf *.o.dSYM
