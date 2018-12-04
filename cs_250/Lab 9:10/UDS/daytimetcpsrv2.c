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
#endif
#include	<time.h>

#define MAXLINE         4096    /* max text line length */
#define LISTENQ         1024    /* 2nd argument to listen() */

int Tcp_listen(const char *host, const char *serv, socklen_t *addrlenp);
char    *Sock_ntop(const struct sockaddr *, socklen_t);
void err_sys(const char *fmt, ...);
void err_quit(const char *fmt, ...);

int main(int argc, char **argv)
{
	int			i, listenfd=0, connfd=0;
	socklen_t		addrlen=0, len=0;
	struct sockaddr	*cliaddr;
	char			buff[MAXLINE];
	time_t			ticks;

	if (argc == 2)
		listenfd = Tcp_listen(NULL, argv[1], &addrlen);
	else if (argc == 3)
		listenfd = Tcp_listen(argv[1], argv[2], &addrlen);
	else
		err_quit("usage: daytimetcpsrv2 [ <host> ] <service or port>");

	cliaddr = malloc(addrlen);

	for ( ; ; ) {
		len = addrlen;
		connfd = accept(listenfd, cliaddr, &len);
		printf("connection from %s\n", Sock_ntop(cliaddr, len));

        ticks = time(NULL);
        snprintf(buff, sizeof(buff), "%.24s\r\n", ctime(&ticks));
		for (i = 0; i < strlen(buff); i++)
        	send(connfd, &buff[i], 1, MSG_EOR);

		close(connfd);
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
