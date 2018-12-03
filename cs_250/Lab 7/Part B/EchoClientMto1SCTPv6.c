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
#include <netinet/in.h>   /* for IVP6 and SCTP support       */
#include <netinet/sctp.h> /* for SCTP support       */
#endif

void DieWithError(char *errorMessage);    /* Error handling function */
static void print_src(int, sctp_assoc_t); //Lab7 Part B - What is this for??

int main(int argc, char *argv[])
{
    int sock_Descr;                   /* Socket descriptor */
    struct sockaddr_in6 echoServAddr; /* Echo server address */
    unsigned short echoServPort;      /* Echo server port */
    char *servIP;                     /* Server IP address (dotted quad) */
    char *echoString;                 /* String to send to echo server */
    unsigned int echoStringLen;       /* Length of string to echo */
    int totalBytesRcvd;               /* Bytes read in single recv() 
                                        and total bytes read */

    //------------------------------------

    // Lab 7 Part B - comment this section
    struct sctp_event_subscribe sctpEventSub;
    bzero(&sctpEventSub, sizeof(sctpEventSub));
    sctpEventSub.sctp_data_io_event = 1;

    struct sctp_sndrcvinfo sndrInfo;
    socklen_t servLen;
    ssize_t sendSize;
    int msgFlags = 0;

    //------------------------------------

    /* ------Step 0 check user input ------ */
    /* Test for correct number of arguments */
    if ((argc < 3) || (argc > 4)) /* Test for correct number of arguments */
    {
        fprintf(stderr, "Usage: %s <Server IP> <Echo Word> [<Echo Port>]\n",
                argv[0]);
        exit(1);
    }

    servIP = argv[1];     /* First arg: server IP address (dotted quad) */
    echoString = argv[2]; /* Second arg: string to echo */

    if (argc == 4)
        echoServPort = atoi(argv[3]); /* Use given port, if any */
    else
        echoServPort = 7; /* 7 is the well-known port for the echo service */

    /* ------Step 1 create socket --------------- */
    if ((sock_Descr = socket(PF_INET6, SOCK_SEQPACKET, IPPROTO_SCTP)) < 0)
        DieWithError("socket() failed");

    //------------------------------------
    // Lab 7 Part B - comment this section
    if (setsockopt(sock_Descr, IPPROTO_SCTP, SCTP_EVENTS, &sctpEventSub, sizeof(sctpEventSub)) < 0)
        DieWithError("setsocketopt() SCTP_Events failed");

    //------------------------------------

    /* Construct the server address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr));                /* Zero out structure */
    echoServAddr.sin6_family = AF_INET6;                           /* Internet address family */
    if (inet_pton(AF_INET6, servIP, &echoServAddr.sin6_addr) <= 0) /* Server IP */
        DieWithError("inet_pton error for input IP address");
    echoServAddr.sin6_port = htons(echoServPort); /* Server port */

    echoStringLen = strlen(echoString); /* Determine input length */

    /* ------Step 2 send message to server ------- */
    /* Send the string to the server */
    //------------------------------------
    // Lab 7 Part B - comment this section - what are the fields for?
    servLen = sizeof(echoServAddr);
    bzero(&sndrInfo, sizeof(sndrInfo));
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
                                                                           //------------------------------------

    /* ------Step 3 recv message from server ------ */
    /* Receive the same string back from the server */
    //------------------------------------
    // Lab 7 Part B - comment this section - what are the fields for?
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
    //------------------------------------

    /* ------Step 4 close connection with server and release resources ------ */
    //------------------------------------
    // Lab 7 Part B - comment this section - what behavior do we expect from the shutdown?
    shutdown(sock_Descr, SHUT_WR);
    //------------------------------------

#ifdef _WINDOWS /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
    WSACleanup()
#endif
        exit(0);
}

//------------------------------------
// Lab 7 Part B - comment this section - What is this function for ?
static void print_src(int fd, sctp_assoc_t assoc_id)
{
    // Lab 7 Part B - comment this section - What are the fields for?
    struct sctp_status sstat;
    struct sctp_paddrinfo *spinfo;
    char tmpname[INET6_ADDRSTRLEN];
    unsigned int port;
    unsigned int ulen;
    struct sockaddr_in6 *s_in;

    bzero(&sstat, sizeof(sstat));

    ulen = sizeof(sstat);
    // Lab 7 Part B - comment this section - What values are we interested in returning?
    if (sctp_opt_info(fd, assoc_id, SCTP_STATUS, &sstat, &ulen) < 0)
    {
        perror("sctp_opt_info()");
        return;
    }
    spinfo = &sstat.sstat_primary;

    // Lab 7 Part B - comment this section - What are we doing on each line?
    s_in = (struct sockaddr_in6 *)&spinfo->spinfo_address;
    inet_ntop(AF_INET, &s_in->sin6_addr, tmpname, sizeof(tmpname));
    port = ntohs(s_in->sin6_port);
    printf("Msg from Server on association - %d IP:Port - %s:%d\n", assoc_id, tmpname, port);
}
//------------------------------------
