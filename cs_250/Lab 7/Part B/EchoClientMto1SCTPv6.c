#include <stdio.h>    /* for printf() and fprintf() */
#include <stdlib.h>   /* for atoi() and exit() */
#ifdef _WINDOWS       /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <winsock2.h> /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <ws2tcpip.h> /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#else
#include <arpa/inet.h>    /* for sockaddr_in and inet_ntoa() */
#include <sys/socket.h>   /* for socket(), bind(), and connect() */
#include <string.h>       /* for memset() */
#include <unistd.h>       /* for close() */
#include <arpa/inet.h>    /* for sockaddr_in and inet_ntoa() */
#include <netdb.h>        /* for IPV6 and SCTP support */
#include <netinet/in.h>   /* for IVP6 and SCTP support */
#include <netinet/sctp.h> /* for SCTP support */
#endif

void DieWithError(char *errorMessage);                             /* Error handling function */
static void print_src(int, sctp_assoc_t);                          /* Prints IP and Port infromation about the server the message was sent and received from */
unsigned short ServiceResolution(char service[], char protocol[]); /* Obtains service port from services file on machine */

int main(int argc, char *argv[])
{
    int sock_Descr;                   /* Socket descriptor */
    struct sockaddr_in6 echoServAddr; /* Echo server address */
    unsigned short echoServPort;      /* Echo server port */
    char *servIP;                     /* Server IP address (dotted quad) */
    char *echoString;                 /* String to send to echo server */
    unsigned int echoStringLen;       /* Length of string to echo */
    int totalBytesRcvd;               /* Bytes read in single recv() and total bytes read */
    struct addrinfo *addptr, hints;   /* Structures used to determine host -> IP information */

    struct sctp_event_subscribe sctpEventSub;   /* SCTP events that should be subscribed to */
    bzero(&sctpEventSub, sizeof(sctpEventSub)); /* Zeros out sctpEventSub */
    sctpEventSub.sctp_data_io_event = 1;        /* Sets sctpEventSub event data event type to enable detailed sender infromation */

    struct sctp_sndrcvinfo sndrInfo; /* Detailed information about the message */
    socklen_t servLen;               /* Length of server IP */
    ssize_t sendSize;                /* Size of message being sent */
    int msgFlags = 0;                /* SCTP socket options passed */

    /* ------Step 0 check user input ------ */
    /* Test for correct number of arguments */
    if ((argc < 3) || (argc > 4)) /* Test for correct number of arguments */
    {
        fprintf(stderr, "Usage: %s <Server IP> <Echo Word> [<Echo Service>]\n",
                argv[0]);
        exit(1);
    }

    servIP = argv[1];     /* First arg: server IP address (dotted quad) OR host from /etc/hosts */
    echoString = argv[2]; /* Second arg: string to echo */

    if (argc == 4)
        echoServPort = ServiceResolution(argv[3], "sctp"); /* Use given service or port, if any */
    else
        echoServPort = ServiceResolution("EchoSCTPService", "sctp"); /* Local port from services file */

    /* ------Step 1 create socket --------------- */
    if ((sock_Descr = socket(PF_INET6, SOCK_SEQPACKET, IPPROTO_SCTP)) < 0)
        DieWithError("socket() failed");

    /* Sets the socket options */
    if (setsockopt(sock_Descr, IPPROTO_SCTP, SCTP_EVENTS, &sctpEventSub, sizeof(sctpEventSub)) < 0)
        DieWithError("setsocketopt() SCTP_Events failed");

    /* Setup structure to determine host -> IP */
    hints.ai_flags = AI_CANONNAME;
    hints.ai_family = AF_INET6;
    hints.ai_socktype = SOCK_SEQPACKET;

    /* Obatains host information */
    if ((getaddrinfo(servIP, "3000", &hints, &addptr)) != 0) /* Arbitrary port is used to ensure function success */
        DieWithError("getaddrinfo() failed");

    /* Converts host information to structure */
    struct sockaddr_in6 *sin6;
    char result6[INET6_ADDRSTRLEN];
    sin6 = (struct sockaddr_in6 *)addptr->ai_addr;

    /* Obtains IP address from sin6 and sets result6 to IP address of server */
    if ((inet_ntop(AF_INET6, &sin6->sin6_addr, result6, sizeof(result6)) == NULL))
        DieWithError("inet_ntop failed");

    /* Construct the server address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr));                 /* Zero out structure */
    echoServAddr.sin6_family = AF_INET6;                            /* Internet address family */
    if (inet_pton(AF_INET6, result6, &echoServAddr.sin6_addr) <= 0) /* Server IP */
        DieWithError("inet_pton error for input IP address");
    echoServAddr.sin6_port = ntohs(echoServPort); /* Server port */

    echoStringLen = strlen(echoString); /* Determine input length */

    /* ------Step 2 send message to server ------- */
    /* Send the string to the server */
    servLen = sizeof(echoServAddr);     /* Length of server address */
    bzero(&sndrInfo, sizeof(sndrInfo)); /* Zeros out sndrInfo */
    /* Ensures that the sent size is equivelent to the size of the string being sent */
    if ((sendSize = sctp_sendmsg(sock_Descr,
                                 echoString,
                                 echoStringLen,
                                 (struct sockaddr *)&echoServAddr,
                                 servLen,
                                 0, 0,
                                 sndrInfo.sinfo_stream,
                                 0, 0)) != echoStringLen)
        DieWithError("send() sent a different number of bytes than expected");
    else
        printf("Sent to the Server: [%.*s]\n", echoStringLen, echoString); /* Print the echo buffer */

    /* ------Step 3 recv message from server ------ */
    /* Receive the same string back from the server */
    /* Ensures that a message is received from the server by verifying the size is greater than 0 */
    if ((totalBytesRcvd = sctp_recvmsg(sock_Descr,
                                       echoString,
                                       sizeof(echoStringLen),
                                       (struct sockaddr *)&echoServAddr,
                                       &servLen,
                                       &sndrInfo,
                                       &msgFlags)) < 0)
        DieWithError("recv() had an error ");
    else
    {
        print_src(sock_Descr, sndrInfo.sinfo_assoc_id);
        printf("Recvd from Server: [%.*s]\n", echoStringLen, echoString); /* Print the echo buffer */
    }

    /* ------Step 4 close connection with server and release resources ------ */
    shutdown(sock_Descr, SHUT_WR); /* Shutsdown the write and read capabilities of the socket */

#ifdef _WINDOWS /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
    WSACleanup()
#endif
        exit(0);
}

/* Prints IP and Port infromation about the server the message was sent and received from */
static void print_src(int fd, sctp_assoc_t assoc_id)
{
    struct sctp_status sstat;       /* Socket option argument  */
    struct sctp_paddrinfo *spinfo;  /* Socket address information */
    char tmpname[INET6_ADDRSTRLEN]; /* Char array to hold IPV6 server IP */
    unsigned int port;              /* Port */
    unsigned int ulen;              /* Length of sctp status */
    struct sockaddr_in6 *s_in;      /* Socket descriptor */

    bzero(&sstat, sizeof(sstat));

    ulen = sizeof(sstat);
    /* Return the information of a SCTP socket option (0 being successful) */
    if (sctp_opt_info(fd, assoc_id, SCTP_STATUS, &sstat, &ulen) < 0)
    {
        perror("sctp_opt_info()");
        return;
    }
    spinfo = &sstat.sstat_primary;

    s_in = (struct sockaddr_in6 *)&spinfo->spinfo_address;                                    /* Obtains socket descriptor from spinfo */
    inet_ntop(AF_INET, &s_in->sin6_addr, tmpname, sizeof(tmpname));                           /* Converts IP address from binary to text */
    port = ntohs(s_in->sin6_port);                                                            /* Obtains port from socket descriptor */
    printf("Msg from Server on association - %d IP:Port - %s:%d\n", assoc_id, tmpname, port); /* Prints formatted message with IP and port */
}
