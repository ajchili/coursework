CC = gcc
CFLAGS = -g -Wall
INCLUDE1=ErrorHandler.c 
INCLUDE2=SignalHandler.c

all: tcpsend01 tcprecv01 tcpsend02 tcprecv02 tcpsend03 tcprecv03 tcpsend04 tcprecv04 \
	tcprecv03p tcpsend05 tcprecv05 tcpsend06 tcprecv06

tcpsend01: tcpsend01.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

tcprecv01: tcprecv01.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) $(INCLUDE2) -o $@.o

tcpsend02: tcpsend02.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

tcprecv02: tcprecv02.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

tcpsend03: tcpsend03.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

tcprecv03: tcprecv03.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

tcprecv03p: tcprecv03p.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

tcpsend04: tcpsend04.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

tcprecv04: tcprecv04.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

tcpsend05: tcpsend05.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

tcprecv05: tcprecv05.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) $(INCLUDE2) -o $@.o

tcpsend06: tcpsend06.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

tcprecv06: tcprecv06.c
	$(CC) $(CFLAGS) $< $(INCLUDE1) -o $@.o

clean:
	-rm -f *.o *~ *core*
	-rm -rf *.o.dSYM
