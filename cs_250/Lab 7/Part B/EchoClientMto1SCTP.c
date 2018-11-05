#include <stdio.h>      	/* for printf() and fprintf() */
#include <stdlib.h>     	/* for atoi() and exit() */
#ifdef _WINDOWS                 /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <winsock2.h>           /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <ws2tcpip.h>           /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#else
#include <arpa/inet.h>  	/* for sockaddr_in and inet_ntoa() */
#include <sys/socket.h> 	/* for socket(), bind(), and connect() */
#include <string.h>     	/* for memset() */
#include <unistd.h>     	/* for close() */
#include <netinet/in.h>  	/* for IVP6 and SCTP support       */
#include <netinet/sctp.h>  	/* for SCTP support       */
#endif

void DieWithError(char *errorMessage);      /* Error handling function */
unsigned long NameResolution(char name[]);  /* Obtains ip address from provided url or name */
static void print_src(int , sctp_assoc_t ); /* Terminal printing helper function */

int main(int argc, char *argv[])
{
    int sock_Descr;                  /* Socket descriptor */
    struct sockaddr_in echoServAddr; /* Echo server address */
    unsigned short echoServPort;     /* Echo server port */
    char *servIP;                    /* Server IP address (dotted quad) */
    char *echoString;                /* String to send to echo server */
    unsigned int echoStringLen;      /* Length of string to echo */
    int totalBytesRcvd;    	         /* Bytes read in single recv() and total bytes read */

    struct sctp_event_subscribe sctpEventSub;   /* STCP subscription object */
    bzero(&sctpEventSub, sizeof(sctpEventSub)); /* Zero out sctp_event_subscribe */
    sctpEventSub.sctp_data_io_event = 1;        /* Allow input and output events subscription */

    struct sctp_sndrcvinfo sndrInfo;            /* Detailed information about the message */
    socklen_t servLen;                          /* Length of server address */
    ssize_t sendSize;                           /* Length of message */
    int msgFlags = 0;                           /* SCTP socket options passed */


    /* ------Step 0 check user input ------ */
    /* Test for correct number of arguments */
    if ((argc < 3) || (argc > 4))    /* Test for correct number of arguments */
    {
       fprintf(stderr, "Usage: %s <Server IP> <Echo Word> [<Echo Port>]\n",
               argv[0]);
       exit(1);
    }

    servIP = argv[1];                                       /* First arg: server IP address (dotted quad) */
    echoString = argv[2];                                   /* Second arg: string to echo */

    if (argc == 4)
        echoServPort = atoi(argv[3]); /* Use given port, if any */
    else
        echoServPort = 7;  /* 7 is the well-known port for the echo service */

    /* ------Step 1 create socket --------------- */
    if ((sock_Descr = socket(PF_INET, SOCK_SEQPACKET, IPPROTO_SCTP)) < 0)
        DieWithError("socket() failed");

    /* Used to specify notification and ancillary data that should be received */
    if (setsockopt(sock_Descr, IPPROTO_SCTP, SCTP_EVENTS, &sctpEventSub, sizeof(sctpEventSub)) < 0 )
        DieWithError("setsocketopt() SCTP_Events failed");

    /* Construct the server address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr));         /* Zero out structure */
    echoServAddr.sin_family      = AF_INET;                 /* Internet address family */
    echoServAddr.sin_addr.s_addr = NameResolution(servIP);  /* Server address (name or IP) */
    echoServAddr.sin_port        = htons(echoServPort);     /* Server port */

    echoStringLen = strlen(echoString);                     /* Determine input length */

    /* ------Step 2 send message to server ------- */
    /* Send the string to the server */
    servLen = sizeof(echoServAddr);     /* Sets the length of the provided server address */
    bzero(&sndrInfo, sizeof(sndrInfo));                /* Zeros out sndrInfo */
    if ((sendSize = sctp_sendmsg(sock_Descr,           /* Socket descriptor */
                    echoString,                        /* Message being send */
                    echoStringLen,                     /* Length of message being sent */
                    (struct sockaddr *) &echoServAddr, /* Server address */
                    servLen,                           /* Length of server address */
                    0,                                 /* Pprotocol payload identifier passed with the data */
                    0,                                 /* SCTP socket options passed */
                    sndrInfo.sinfo_stream,             /* SCTP stream number */
                    0,                                 /* Time to live */
                    0)) != echoStringLen)              /* Context */
        DieWithError("send() sent a different number of bytes than expected");
    else
        printf("Sent to the Server: [%.*s]\n",echoStringLen, echoString);      /* Print the echo buffer */
        
    /* ------Step 3 recv message from server ------ */
    /* Receive the same string back from the server */
    if ((totalBytesRcvd = sctp_recvmsg(sock_Descr,           /* Socket */
                          echoString,                        /* Message being received */
                          sizeof(echoStringLen),             /* Length of message being received */
                          (struct sockaddr *) &echoServAddr, /* Server address */
                          &servLen,                          /* Length of server address */
                          &sndrInfo,                         /* Detailed information about the message */
                          &msgFlags)) < 0 )                  /* Flags */
        DieWithError("recv() had an error ");
    else {
        print_src(sock_Descr, sndrInfo.sinfo_assoc_id);
        printf("Recvd from Server: [%.*s]\n",echoStringLen, echoString);      /* Print the echo buffer */
    }


    /* ------Step 4 close connection with server and release resources ------ */
    /* Shuts down communication on socket descriptor */
    /* SHUT_WR disables further send operations and initiates the shutdown to terminate the association (also allows local endpoint to read the data queued prior to SHUTDOWN */
    shutdown(sock_Descr, SHUT_WR);

#ifdef _WINDOWS			/* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
    WSACleanup()
#endif
    exit(0);
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

