CC = gcc
CFLAGS = -g -Wall
INCLUDE1=ErrorHandler.c
INCLUDE2=SignalHandler.c
INCLUDE3=sock_ntop_host.c
INCLUDE4=sock_ntop.c

PROGS =	daytimetcpcli daytimetcpsrv2 mycat openfile \
	unixbind unixstrcli01 unixstrserv01 unixstrserv02

all:	${PROGS}

daytimetcpcli:	daytimetcpcli.c
		${CC} ${CFLAGS} $< ${INCLUDE1} ${INCLUDE3} -o $@.o 

daytimetcpsrv2:	daytimetcpsrv2.c
		${CC} ${CFLAGS} $< ${INCLUDE1} ${INCLUDE4} -o $@.o 

mycat:		mycat.c myopen.c
		${CC} ${CFLAGS} $< myopen.c read_fd.c write_fd.c ${INCLUDE1} -o $@.o

openfile:	openfile.c
		${CC} ${CFLAGS} $< write_fd.c ${INCLUDE1} -o $@.o 

unixbind:	unixbind.c
		${CC} ${CFLAGS} $< ${INCLUDE1} -o $@.o 

unixstrcli01:	unixstrcli01.c
		${CC} ${CFLAGS} $< str_cli.c readline.c writen.c ${INCLUDE1} -o $@.o

unixstrserv01:	unixstrserv01.c sigchldwaitpid.c
		${CC} ${CFLAGS} $< sigchldwaitpid.c writen.c ${INCLUDE1} -o $@.o 

unixstrserv02:	unixstrserv02.c sigchldwaitpid.c readcred.c
		${CC} ${CFLAGS} $< sigchldwaitpid.c writen.c ${INCLUDE1} -o $@.o 

clean:
		rm -f ${PROGS} ${CLEANFILES}
