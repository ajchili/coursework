CC = gcc
CFLAGS = -g -Wall -pthread
INCLUDE1=DieWithError.c HandleClientTCP.c
INCLUDE2=DieWithError.c CaesarCipher.c GetServerDateTime.c GetServerQuote.c GetServerCountryData.c

all: Application EchoServerTCP-Forked-Num

Application: Application.c
	$(CC) $(CFLAGS) $(INCLUDE2) -o $@.o $<

EchoServerTCP-Forked-Num: EchoServerTCP-Forked-Num.c
	$(CC) $(CFLAGS) $(INCLUDE1) -o $@.o $<

clean:
	-rm -f *.o *~ *core* 
