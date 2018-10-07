CC = gcc
CFLAGS = -g -Wall
PTHREAD = -pthread
INCLUDE2=DieWithError.c HandleClientTCP.c

all: EchoServerTCP-Forked EchoServerTCP-Thread EchoServerTCP-Forked-Num

EchoServerTCP-Forked: EchoServerTCP-Forked.c
	$(CC) $(CFLAGS) $(PTHREAD) $(INCLUDE2) -o $@.o $<

EchoServerTCP-Forked-Num: EchoServerTCP-Forked-Num.c
	$(CC) $(CFLAGS) $(PTHREAD) $(INCLUDE2) -o $@.o $<

EchoServerTCP-Thread: EchoServerTCP-Thread.c
	$(CC) $(CFLAGS) $(PTHREAD) $(INCLUDE2) -o $@.o $<
clean:
	-rm -f *.o *~ *core* 
