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
#endif

int Tcp_connect(const char *host, const char *serv);
void err_sys(const char *fmt, ...);
void err_quit(const char *fmt, ...);

int main(int argc, char **argv)
{
	int		sockfd;

	if (argc != 3)
		err_quit("usage: tcpsend02 <host> <port#>");

	sockfd = Tcp_connect(argv[1], argv[2]);

	write(sockfd, "123", 3);
	printf("wrote 3 bytes of normal data\n");
	sleep(1);

	send(sockfd, "4", 1, MSG_OOB);
	printf("wrote 1 byte of OOB data\n");
	sleep(1);

	write(sockfd, "56", 2);
	printf("wrote 2 bytes of normal data\n");
	sleep(1);

	send(sockfd, "7", 1, MSG_OOB);
	printf("wrote 1 byte of OOB data\n");
	sleep(1);

	write(sockfd, "89", 2);
	printf("wrote 2 bytes of normal data\n");
	sleep(1);

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
