#include <stdio.h>      	/* for printf() and fprintf() */
#include <stdlib.h>     	/* for atoi() and exit() */
#ifdef _WIN32                   /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <winsock2.h>           /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <ws2tcpip.h>           /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#else
#include <arpa/inet.h>  	/* for sockaddr_in and inet_ntoa() */
#include <sys/socket.h> 	/* for socket(), bind(), and connect() */
#include <string.h>     	/* for memset() */
#include <unistd.h>     	/* for close() */
#include <netinet/in.h>  	/* for IPV6,  SCTP support         */
#include <netinet/sctp.h>  	/* for SCTP support       */
#endif

#define MAXBUFF		80	
#define MAXPENDING	5

void DieWithError(char *errorMessage);      /* Error handling helper function */
unsigned long NameResolution(char name[]);  /* Obtains ip address from provided url or name */
static void print_src(int , sctp_assoc_t ); /* Terminal printing helper function */

int main(int argc, char *argv[])
{
    int servSock_d;                     /* Socket descriptor for server */
    struct sockaddr_in echoServAddr;    /* Local address */
    struct sockaddr_in echoClntAddr;    /* Client address */
    unsigned short echoServPort;        /* Server port */

    char readBuffer[MAXBUFF-1];                   /* Maximum buffer allocated for messages */
    struct sctp_event_subscribe sctpEventSub;     /* STCP subscription object */
    bzero(&sctpEventSub, sizeof(sctpEventSub));   /* Zero out sctp_event_subscribe */
    sctpEventSub.sctp_data_io_event = 1;          /* Allow input and output events subscription */

    struct sctp_sndrcvinfo sndrInfo;              /* Detailed information about the message */
    socklen_t clntLen;                            /* Length of client address data structure */
    ssize_t readSize;                             /* Length of message received from client */
    int reUse = 1;                                /* Should sockets be reused for new connections */
    int maxIdle = 1;                              /* Maximum idle length */
    int msgFlags;                                 /* SCTP socket options passed */

    /* ------Step 0 check user input ------ */
    /* Test for correct number of arguments from user */
    if (argc != 2) {     
        fprintf(stderr, "Usage:  %s <Server Port>\n", argv[0]);
        exit(1);
    }

    echoServPort = atoi(argv[1]);  	/* First arg:  should be local port */

    /* ------Step 1 create the socket ------- */
    /* Create socket for incoming connections */
    if ((servSock_d = socket(PF_INET, SOCK_SEQPACKET, IPPROTO_SCTP)) < 0)
        DieWithError("socket() failed");
      
    /* Construct local address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr));   /* Zero out structure */
    echoServAddr.sin_family = AF_INET;                /* Internet address family */
    echoServAddr.sin_addr.s_addr = htonl(INADDR_ANY); /* Any incoming interface */
    echoServAddr.sin_port = htons(echoServPort);      /* Local port */

    /* ------Step 2 bind the connection ip:port ------- */
    /* Bind to the local address */
    if (bind(servSock_d, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) < 0)
        DieWithError("bind() failed");

    /* Reuses sockets for new connections */
    if (setsockopt(servSock_d, SOL_SOCKET, SO_REUSEADDR, &reUse, sizeof(reUse)) < 0 )
        DieWithError("setsocketopt() SO_REUSEADDR failed");

    /* Auto closes connections that are idel for longer than the specified idle length */
    if (setsockopt(servSock_d, IPPROTO_SCTP, SCTP_AUTOCLOSE, &maxIdle, sizeof(maxIdle)) < 0 )
        DieWithError("setsocketopt() SCTP_AUTOCLOSE failed");

    /* Used to specify notification and ancillary data that should be received */
    if (setsockopt(servSock_d, IPPROTO_SCTP, SCTP_EVENTS, &sctpEventSub, sizeof(sctpEventSub)) < 0 )
        DieWithError("setsocketopt() SCTP_Events failed");

    /* ------Step 3 listen to the socket for a connection ------- */
    /* Establish the socket to listen for incoming connections */
    if (listen(servSock_d, MAXPENDING) < 0)
        DieWithError("listen() failed");

    for (;;) /* Infinite Loop - run until process killed */
    {
        /* Set the size of the in-out parameter */
        clntLen = sizeof (struct sockaddr_in);

        /* ------Step 4 recv from the client ------- */
        /* Reads provided message from client and obtains size of message */
        readSize = sctp_recvmsg(servSock_d,                        /* Socket */
                                readBuffer,                        /* Buffer of received message */
                                sizeof(readBuffer),                /* Size of buffer */
                                (struct sockaddr *) &echoClntAddr, /* Address of client */
                                &clntLen,                          /* Length of client address */
                                &sndrInfo,                         /* Detailed information about the message */
                                &msgFlags);                        /* Flags */
       if (readSize > 0) {
          print_src(servSock_d, sndrInfo.sinfo_assoc_id);
          printf("Msg recvd from client: [%.*s]\n",(int) readSize, readBuffer );
       } 

        /* ------Step 5 send to the client --------- */
        sctp_sendmsg(servSock_d,                        /* Socket */
                    readBuffer,                        /* Buffer of received message */
                    readSize,                          /* Size of buffer */
                    (struct sockaddr *) &echoClntAddr, /* Address of client */
                    clntLen,                           /* Length of client address */
                    sndrInfo.sinfo_ppid,               /* Protocol payload identifier passed with the data */
                    sndrInfo.sinfo_flags,              /* SCTP socket options */
                    sndrInfo.sinfo_stream,             /* SCTP stream number */
                    0,                                 /* Time to live */
                    0);                                /* Context: provides info for failed-message transmission */
    } 

    /* ------Step 5+ implied close when program is terminated ------- */
}

/* Prints socket descriptor data for provided association id */
static void print_src(int fd, sctp_assoc_t assoc_id)
{
        struct sctp_status sstat;      /* SCTP Stats */
        struct sctp_paddrinfo *spinfo; /* SCTP address information */
        char tmpname[INET_ADDRSTRLEN]; /* IP address or name of socket */
        unsigned int port;             /* Port */
        unsigned int ulen;             /* Socket length */
        struct sockaddr_in *s_in;      /* Socket descriptor */

        bzero(&sstat, sizeof (sstat)); /* Zero out SCTP Stats */

        ulen = sizeof (sstat);         /* Sets ulen to size of sstat */
        
        /* Obtains and returns SCTP level optioms that are associated with the provided socket */
        if (sctp_opt_info(fd, assoc_id, SCTP_STATUS, &sstat, &ulen) < 0) {
            perror("sctp_opt_info()");
            return;
        }
        spinfo = &sstat.sstat_primary;

        /* Sets socket descriptor then prints out that a message is received and the socket descriptor data */
        s_in = (struct sockaddr_in *) &spinfo->spinfo_address;
        inet_ntop(AF_INET, &s_in->sin_addr, tmpname, sizeof (tmpname));
        port = ntohs(s_in->sin_port);
        printf("Msg from Server on association - %d IP:Port - %s:%d\n", assoc_id, tmpname, port);
}
