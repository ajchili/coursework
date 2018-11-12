CC = gcc
CFLAGS = -g -Wall -pthread
INCLUDE1=DieWithError.c
INCLUDE2=DieWithError.c HandleClientTCP.c

all: EchoClientTCP EchoServerTCP-Forked-Num CaesarCipher

EchoClientTCP: EchoClientTCP.c
	$(CC) $(CFLAGS) $(INCLUDE1) -o $@.o $<

EchoServerTCP-Forked-Num: EchoServerTCP-Forked-Num.c
	$(CC) $(CFLAGS) $(INCLUDE2) -o $@.o $<

CaesarCipher: CaesarCipher.c
	$(CC) $(CFLAGS) -o $@.o $<

clean:
	-rm -f *.o *~ *core* 
