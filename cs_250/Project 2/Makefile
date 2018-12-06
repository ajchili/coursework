CC = gcc
CFLAGS = -g -Wall
INCLUDE1=DieWithError.c

all: Application SigAction MulticastSender MulticastReceiver BroadcastSender BroadcastReceiver

Application: Application.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

SigAction: SigAction.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

MulticastSender: MulticastSender.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

MulticastReceiver: MulticastReceiver.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

BroadcastSender: BroadcastSender.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

BroadcastReceiver: BroadcastReceiver.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

clean:
	-rm -f *.o *~ *core*
	-rm -rf *.o.dSYM
