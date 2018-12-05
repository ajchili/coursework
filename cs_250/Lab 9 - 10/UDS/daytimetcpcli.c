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
//#include <sys/un.h>     /* for sockaddr_un */
#endif

#define MAXLINE         4096    /* max text line length */

int Tcp_connect(const char *host, const char *serv);
char    *Sock_ntop_host(const struct sockaddr *, socklen_t);
void err_sys(const char *fmt, ...);
void err_quit(const char *fmt, ...);

int main(int argc, char **argv)
{
	int			sockfd, n;
	char			recvline[MAXLINE + 1];
	socklen_t		len;
	struct sockaddr	*sa;

	if (argc != 3)
		err_quit("usage: daytimetcpcli <hostname/IPaddress> <service/port#>");

	sockfd = Tcp_connect(argv[1], argv[2]);

	sa = malloc(sizeof(struct sockaddr_storage));
	len = sizeof(struct sockaddr_storage);
	getpeername(sockfd, sa, &len);
	printf("connected to %s\n", Sock_ntop_host(sa, len));
	sleep(5);

	while ( (n = read(sockfd, recvline, MAXLINE)) > 0) {
		recvline[n] = 0;	/* null terminate */
		printf("%d bytes: %s", n, recvline);
	}
	exit(0);
}
//////////
int tcp_connect(const char *host, const char *serv)
{
        int                             sockfd, n;
        struct addrinfo hints, *res, *ressave;

        bzero(&hints, sizeof(struct addrinfo));
        hints.ai_family = AF_UNSPEC;
        hints.ai_socktype = SOCK_STREAM;

        if ( (n = getaddrinfo(host, serv, &hints, &res)) != 0)
                err_quit("tcp_connect error for %s, %s: %s",
                                 host, serv, gai_strerror(n));
        ressave = res;

        do {
                sockfd = socket(res->ai_family, res->ai_socktype, res->ai_protocol);
                if (sockfd < 0)
                        continue;       /* ignore this one */

                if (connect(sockfd, res->ai_addr, res->ai_addrlen) == 0)
                        break;          /* success */

                close(sockfd);  /* ignore this one */
        } while ( (res = res->ai_next) != NULL);

        if (res == NULL)        /* errno set from final connect() */
                err_sys("tcp_connect error for %s, %s", host, serv);

        freeaddrinfo(ressave);

        return(sockfd);
}
/* end tcp_connect */

int Tcp_connect(const char *host, const char *serv)
{
        return(tcp_connect(host, serv));
}
