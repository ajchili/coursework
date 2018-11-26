CC = gcc
CFLAGS = -g -Wall -pthread
INCLUDE1=DieWithError.c CaesarCipher.c ReadQuotes.c ReadCountries.c HandleClientTCP.c HandleClientUDP.c
INCLUDE2=DieWithError.c CaesarCipher.c GetServerDateTime.c GetServerQuote.c GetServerCountryData.c

all: Application Server-Forked-Num

Application: Application.c
	$(CC) $(CFLAGS) $(INCLUDE2) -o $@.o $<

Server-Forked-Num: Server-Forked-Num.c
	$(CC) $(CFLAGS) $(INCLUDE1) -o $@.o $<

clean:
	-rm -f *.o *~ *core* 
