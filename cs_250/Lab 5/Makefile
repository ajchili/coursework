CC = gcc
CFLAGS = -g -Wall
INCLUDE1=DieWithError.c
INCLUDE2=DieWithError.c

all: EchoClientUDP EchoServerUDP

EchoClientUDP: EchoClientUDP.c
	$(CC) $(CFLAGS) $(INCLUDE1) -o $@.o $<

EchoServerUDP: EchoServerUDP.c
	$(CC) $(CFLAGS) $(INCLUDE2) -o $@.o $<

clean:
	-rm -f *.o *~ *core* 
