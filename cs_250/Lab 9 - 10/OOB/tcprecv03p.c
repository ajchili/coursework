#include <stdio.h>      /* for printf() and fprintf() */
#include <stdlib.h>     /* for atoi() and exit() */
#ifdef _WINDOWS                 /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <winsock2.h>           /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <ws2tcpip.h>           /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#else
#include <string.h>     /* for memset() */
#include <sys/socket.h> /* for socket(), connect(), sendto(), and recvfrom() */
#include <arpa/inet.h>  /* for sockaddr_in and inet_addr() */
#include <unistd.h>     /* for close() */
#include <netdb.h>      /* for getaddrinfo and address structure */
#include <fcntl.h>      /* for fcntl() */
#include <signal.h>     /* for Signal() */
#include <poll.h>       /* for poll() */
#endif
/* POSIX requires that an #include of <poll.h> DefinE INFTIM, but many
   systems still DefinE it in <sys/stropts.h>.  We don't want to include
   all the STREAMS stuff if it's not needed, so we just DefinE INFTIM here.
   This is the standard value, but there's no guarantee it is -1. */
#ifndef INFTIM
#define INFTIM          (-1)    /* infinite poll timeout */
/* $$.Ic INFTIM$$ */
#ifdef  HAVE_POLL_H
#define INFTIM_UNPH                             /* tell unpxti.h we defined it */
#endif
#endif

/* Following could be derived from SOMAXCONN in <sys/socket.h>, but many
   kernels still #define it as 5, while actually supporting many more */
#define LISTENQ         1024    /* 2nd argument to listen() */

typedef void    Sigfunc(int);   /* for signal handlers */

int Tcp_listen(const char *host, const char *serv, socklen_t *addrlenp);
void    sig_urg(int);
Sigfunc  *Signal(int, Sigfunc *);
void err_quit(const char *fmt, ...);
void err_sys(const char *fmt, ...);

int main(int argc, char **argv)
{
	int	listenfd=0, connfd=0, n, justreadoob = 0;
	char	buff[100];
	struct pollfd	pollfd[1];

	if (argc == 2)
		listenfd = Tcp_listen(NULL, argv[1], NULL);
	else if (argc == 3)
		listenfd = Tcp_listen(argv[1], argv[2], NULL);
	else
		err_quit("usage: tcprecv03p [ <host> ] <port#>");

	connfd = accept(listenfd, NULL, NULL);

	pollfd[0].fd = connfd;
	pollfd[0].events = POLLRDNORM;
	for ( ; ; ) {
		if (justreadoob == 0)
			pollfd[0].events |= POLLRDBAND;

		poll(pollfd, 1, INFTIM);

		if (pollfd[0].revents & POLLRDBAND) {
			n = recv(connfd, buff, sizeof(buff)-1, MSG_OOB);
			buff[n] = 0;		/* null terminate */
			printf("read %d OOB byte: %s\n", n, buff);
			justreadoob = 1;
			pollfd[0].events &= ~POLLRDBAND;	/* turn bit off */
		}

		if (pollfd[0].revents & POLLRDNORM) {
			if ( (n = read(connfd, buff, sizeof(buff)-1)) == 0) {
				printf("received EOF\n");
				exit(0);
			}
			buff[n] = 0;	/* null terminate */
			printf("read %d bytes: %s\n", n, buff);
			justreadoob = 0;
		}
	}
}
int tcp_listen(const char *host, const char *serv, socklen_t *addrlenp)
{
        int                             listenfd, n;
        const int               on = 1;
        struct addrinfo hints, *res, *ressave;

        bzero(&hints, sizeof(struct addrinfo));
        hints.ai_flags = AI_PASSIVE;
        hints.ai_family = AF_UNSPEC;
        hints.ai_socktype = SOCK_STREAM;

        if ( (n = getaddrinfo(host, serv, &hints, &res)) != 0)
                err_quit("tcp_listen error for %s, %s: %s",
                                 host, serv, gai_strerror(n));
        ressave = res;

        do {
                listenfd = socket(res->ai_family, res->ai_socktype, res->ai_protocol);
                if (listenfd < 0)
                        continue;               /* error, try next one */

                setsockopt(listenfd, SOL_SOCKET, SO_REUSEADDR, &on, sizeof(on));
                if (bind(listenfd, res->ai_addr, res->ai_addrlen) == 0)
                        break;                  /* success */

                close(listenfd);        /* bind error, close and try next one */
        } while ( (res = res->ai_next) != NULL);

        if (res == NULL)        /* errno from final socket() or bind() */
                err_sys("tcp_listen error for %s, %s", host, serv);

        listen(listenfd, LISTENQ);

        if (addrlenp)
                *addrlenp = res->ai_addrlen;    /* return size of protocol address */

        freeaddrinfo(ressave);
        return(listenfd);
}
/* end tcp_listen */

int Tcp_listen(const char *host, const char *serv, socklen_t *addrlenp)
{
        return(tcp_listen(host, serv, addrlenp));
}
