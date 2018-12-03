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
#include <netinet/in.h>   /* for IPV6,  SCTP support         */
#include <netinet/sctp.h> /* for SCTP support       */
#endif

#define MAXBUFF 80
#define MAXPENDING 5

void DieWithError(char *errorMessage);    /* Error handling helper function */
static void print_src(int, sctp_assoc_t); //Lab7 Part B - What is this for??

int main(int argc, char *argv[])
{
    int servSock_d;                   /* Socket descriptor for server */
    struct sockaddr_in6 echoServAddr; /* Local address */
    struct sockaddr_in6 echoClntAddr; /* Client address */
    unsigned short echoServPort;      /* Server port */

    //------------------------------------
    //Lab7 Part B - What are these fields for?
    char readBuffer[MAXBUFF - 1];
    struct sctp_event_subscribe sctpEventSub;
    bzero(&sctpEventSub, sizeof(sctpEventSub));
    sctpEventSub.sctp_data_io_event = 1;

    struct sctp_sndrcvinfo sndrInfo;
    socklen_t clntLen;
    ssize_t readSize;
    int reUse = 1;
    int maxIdle = 1;
    int msgFlags;

    //------------------------------------

    /* ------Step 0 check user input ------ */
    /* Test for correct number of arguments from user */
    if (argc != 2)
    {
        fprintf(stderr, "Usage:  %s <Server Port>\n", argv[0]);
        exit(1);
    }

    echoServPort = atoi(argv[1]); /* First arg:  should be local port */

    /* ------Step 1 create the socket ------- */
    /* Create socket for incoming connections */
    if ((servSock_d = socket(PF_INET6, SOCK_SEQPACKET, IPPROTO_SCTP)) < 0)
        DieWithError("socket() failed");

    /* Construct local address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr)); /* Zero out structure */
    echoServAddr.sin6_family = AF_INET6;            /* Internet address family */
    echoServAddr.sin6_addr = in6addr_any;           /* Any incoming interface */
    echoServAddr.sin6_port = htons(echoServPort);   /* Local port */

    /* ------Step 2 bind the connection ip:port ------- */
    /* Bind to the local address */
    if (bind(servSock_d, (struct sockaddr *)&echoServAddr, sizeof(echoServAddr)) < 0)
        DieWithError("bind() failed");

    //------------------------------------
    //Lab7 Part B - Why are we setting the following options and what arew we setting them to?

    if (setsockopt(servSock_d, SOL_SOCKET, SO_REUSEADDR, &reUse, sizeof(reUse)) < 0)
        DieWithError("setsocketopt() SO_REUSEADDR failed");
    if (setsockopt(servSock_d, IPPROTO_SCTP, SCTP_AUTOCLOSE, &maxIdle, sizeof(maxIdle)) < 0)
        DieWithError("setsocketopt() SCTP_AUTOCLOSE failed");
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
        //------------------------------------
        //Lab7 Part B - Why are the fields and what are we doing?
        readSize = sctp_recvmsg(servSock_d,
                                readBuffer,
                                sizeof(readBuffer),
                                (struct sockaddr *)&echoClntAddr,
                                &clntLen,
                                &sndrInfo,
                                &msgFlags);
        if (readSize > 0)
        {
            print_src(servSock_d, sndrInfo.sinfo_assoc_id);
            printf("Msg recvd from client: [%.*s]\n", (int)readSize, readBuffer);
        }
        //------------------------------------

        /* ------Step 5 send to the client --------- */
        //------------------------------------
        //Lab7 Part B - Why are the fields and what are we doing?
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

//------------------------------------
//Lab7 Part B - Comments should be the same for here as in EchoClientMto1SCTP.c just copy here
static void print_src(int fd, sctp_assoc_t assoc_id)
{
    struct sctp_status sstat;
    struct sctp_paddrinfo *spinfo;
    char tmpname[INET6_ADDRSTRLEN];
    unsigned int port;
    unsigned int ulen;
    struct sockaddr_in6 *s_in;

    bzero(&sstat, sizeof(sstat));

    ulen = sizeof(sstat);
    if (sctp_opt_info(fd, assoc_id, SCTP_STATUS, &sstat, &ulen) < 0)
    {
        perror("sctp_opt_info()");
        return;
    }
    spinfo = &sstat.sstat_primary;

    s_in = (struct sockaddr_in6 *)&spinfo->spinfo_address;
    inet_ntop(AF_INET6, &s_in->sin6_addr, tmpname, sizeof(tmpname));
    port = ntohs(s_in->sin6_port);
    printf("Msg from client on association - %d IP:Port - %s:%d\n", assoc_id, tmpname, port);
    //------------------------------------
}
