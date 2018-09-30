CC = gcc
CFLAGS = -g -Wall
INCLUDE1=DieWithError.c
INCLUDE2=DieWithError.c HandleClientTCP.c

all: EchoClientTCP EchoServerTCP

EchoClientTCP: EchoClientTCP.c
	$(CC) $(CFLAGS) $(INCLUDE1) -o $@.o $<

EchoServerTCP: EchoServerTCP.c
	$(CC) $(CFLAGS) $(INCLUDE2) -o $@.o $<

# clean:
# 	-rm -f *.o *~ *core* 
