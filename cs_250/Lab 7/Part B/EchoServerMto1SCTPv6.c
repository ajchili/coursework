#include <stdio.h>    /* for printf() and fprintf() */
#include <stdlib.h>   /* for atoi() and exit() */
#ifdef _WIN32         /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <winsock2.h> /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <ws2tcpip.h> /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#else
#include <arpa/inet.h>    /* for sockaddr_in and inet_ntoa() */
#include <sys/socket.h>   /* for socket(), bind(), and connect() */
#include <string.h>       /* for memset() */
#include <unistd.h>       /* for close() */
#include <netinet/in.h>   /* for IPV6 and SCTP support */
#include <netinet/sctp.h> /* for SCTP support */
#endif

#define MAXBUFF 80
#define MAXPENDING 5

void DieWithError(char *errorMessage);                             /* Error handling helper function */
static void print_src(int, sctp_assoc_t);                          /* Prints IP and Port infromation about the server the message was sent and received from */
unsigned short ServiceResolution(char service[], char protocol[]); /* Obtains service port from services file on machine */

int main(int argc, char *argv[])
{
    int servSock_d;                   /* Socket descriptor for server */
    struct sockaddr_in6 echoServAddr; /* Local address */
    struct sockaddr_in6 echoClntAddr; /* Client address */
    unsigned short echoServPort;      /* Server port */

    char readBuffer[MAXBUFF - 1];               /* Maximum read buffer for messages received by the server */
    struct sctp_event_subscribe sctpEventSub;   /* SCTP events that should be subscribed to */
    bzero(&sctpEventSub, sizeof(sctpEventSub)); /* Zeros out sctpEventSub */
    sctpEventSub.sctp_data_io_event = 1;        /* Sets sctpEventSub event data event type to enable detailed sender infromation */

    struct sctp_sndrcvinfo sndrInfo; /* Detailed information about the message */
    socklen_t clntLen;               /* Length of client IP */
    ssize_t readSize;                /* Size of message received from client */
    int reUse = 1;                   /* Allow reuse of local addresses */
    int maxIdle = 1;                 /* Associations that are idle for more than the specified number of seconds to automatically close */
    int msgFlags;                    /* SCTP socket options passed */

    if (argc == 2)
        echoServPort = ServiceResolution(argv[1], "sctp"); /* Local port or service provided by user */
    else
        echoServPort = ServiceResolution("EchoSCTPService", "sctp"); /* Local port from services file */

    /* ------Step 1 create the socket ------- */
    /* Create socket for incoming connections */
    if ((servSock_d = socket(PF_INET6, SOCK_SEQPACKET, IPPROTO_SCTP)) < 0)
        DieWithError("socket() failed");

    /* Construct local address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr)); /* Zero out structure */
    echoServAddr.sin6_family = AF_INET6;            /* Internet address family */
    echoServAddr.sin6_addr = in6addr_any;           /* Any incoming interface */
    echoServAddr.sin6_port = ntohs(echoServPort);   /* Local port */

    /* ------Step 2 bind the connection ip:port ------- */
    /* Bind to the local address */
    if (bind(servSock_d, (struct sockaddr *)&echoServAddr, sizeof(echoServAddr)) < 0)
        DieWithError("bind() failed");

    /* Allows the socket to reuse the same address for specified reUse */
    if (setsockopt(servSock_d, SOL_SOCKET, SO_REUSEADDR, &reUse, sizeof(reUse)) < 0)
        DieWithError("setsocketopt() SO_REUSEADDR failed");
    /* Allows SCTP socket to auto close after specified maxIdle time */
    if (setsockopt(servSock_d, IPPROTO_SCTP, SCTP_AUTOCLOSE, &maxIdle, sizeof(maxIdle)) < 0)
        DieWithError("setsocketopt() SCTP_AUTOCLOSE failed");
    /* Sets the socket options */
    if (setsockopt(servSock_d, IPPROTO_SCTP, SCTP_EVENTS, &sctpEventSub, sizeof(sctpEventSub)) < 0)
        DieWithError("setsocketopt() SCTP_Events failed");

    //------------------------------------

    /* ------Step 3 listen to the socket for a connection ------- */
    /* Establish the socket to listen for incoming connections */
    if (listen(servSock_d, MAXPENDING) < 0)
        DieWithError("listen() failed");

    for (;;) /* Infinite Loop - run until process killed */
    {
        /* Set the size of the in-out parameter */
        clntLen = sizeof(struct sockaddr_in6);

        /* ------Step 4 recv from the client ------- */
        /* Reads message that is sent by client on the socket */
        readSize = sctp_recvmsg(servSock_d,
                                readBuffer,
                                sizeof(readBuffer),
                                (struct sockaddr *)&echoClntAddr,
                                &clntLen,
                                &sndrInfo,
                                &msgFlags);
        /* Ensures a message is actually received */
        if (readSize > 0)
        {
            print_src(servSock_d, sndrInfo.sinfo_assoc_id);
            printf("Msg recvd from client: [%.*s]\n", (int)readSize, readBuffer);
        }

        /* ------Step 5 send to the client --------- */
        /* Sends received message back to client (echoing) */
        sctp_sendmsg(servSock_d,
                     readBuffer,
                     readSize,
                     (struct sockaddr *)&echoClntAddr,
                     clntLen,
                     sndrInfo.sinfo_ppid,
                     sndrInfo.sinfo_flags,
                     sndrInfo.sinfo_stream,
                     0, 0);
    }
    //------------------------------------
    /* ------Step 5+ implied close when program is terminated ------- */
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
