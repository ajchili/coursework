#include <stdio.h>      /* for printf() and fprintf() */
#include <sys/socket.h> /* for socket() and bind() */
#include <arpa/inet.h>  /* for sockaddr_in */
#include <stdlib.h>     /* for atoi() and exit() */
#include <string.h>     /* for memset() */
#include <unistd.h>     /* for close() */
#include <signal.h>     /* for close() */

int  	 DaemonInit(const char *, int);     /* External Call to DaemonInit */
void     err_msg(const char *, ...);        /* External use of ErrorHandler.c functions */
void     err_quit(const char *, ...);       /* External use of ErrorHandler.c functions */

void InterruptSignalHandler(int );          /* Locally defined Signal handling function */
int interruptCnt = 0;      /* Makes this signal handler stubborn - you have to do it three times to exit */

int main(int argc, char *argv[])
{
    int sock;                         /* Socket */
    struct sockaddr_in broadcastAddr; /* Broadcast address */
    char *broadcastIP;                /* IP broadcast address */
    unsigned short broadcastPort;     /* Server port */
    char *sendString;                 /* String to broadcast */
    int broadcastPermission;          /* Socket opt to set permission to broadcast */
    unsigned int sendStringLen;       /* Length of string to broadcast */
    /* Signal handling variables */
    struct sigaction handler;         /* Signal handler specification structure */

    if (argc < 4)                     /* Test for correct number of parameters */
    {
        err_quit("Usage:  %s <IP Address> <Port> <Send String>\n", argv[0]);
    }

    /* Daemonize this application, kill parent and fork new child to continue */
    DaemonInit(argv[0], 0);

    /* Set InterruptSignalHandler() as handler function */
    handler.sa_handler =  InterruptSignalHandler;
    /* Create mask that mask all signals */
    if (sigfillset(&handler.sa_mask) < 0) 
        err_quit("sigfillset() failed in program %s", argv[0]);
    /* No flags */
    handler.sa_flags = 0;
    
    /* Set signal handling for interrupt signals */
    if (sigaction(SIGINT, &handler, 0) < 0)
        err_quit("sigaction() failed in program %s", argv[0]);
    /* ------------------------------------------------ */


    /* Continue with the rest of the program as usual-- */
    /* --- Just change to using syslogd err-msg model-- */
    broadcastIP = argv[1];            /* First arg:  broadcast IP address */ 
    broadcastPort = atoi(argv[2]);    /* Second arg:  broadcast port */
    sendString = argv[3];             /* Third arg:  string to broadcast */

    /* Create socket for sending/receiving datagrams */
    if ((sock = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0)
        err_quit("socket() failed in program %s", argv[0]);

    /* Set socket to allow broadcast */
    broadcastPermission = 1;
    if (setsockopt(sock, SOL_SOCKET, SO_BROADCAST, (void *) &broadcastPermission, 
          sizeof(broadcastPermission)) < 0)
        err_quit("setsocketopt() failed in program %s", argv[0]);

    /* Construct local address structure */
    memset(&broadcastAddr, 0, sizeof(broadcastAddr));   /* Zero out structure */
    broadcastAddr.sin_family = AF_INET;                 /* Internet address family */
    broadcastAddr.sin_addr.s_addr = inet_addr(broadcastIP);/* Broadcast IP address */
    broadcastAddr.sin_port = htons(broadcastPort);         /* Broadcast port */

    sendStringLen = strlen(sendString);  /* Find length of sendString */
    for (;;) /* Run forever */
    {
         /* Broadcast sendString in datagram to clients every 3 seconds*/
         if (sendto(sock, sendString, sendStringLen, 0, (struct sockaddr *) 
               &broadcastAddr, sizeof(broadcastAddr)) != sendStringLen)
             err_quit("sendto() sent a differnt number of bytes - in program %s", argv[0]);

        /* not an error just a message to the syslog for auditing purposes */
        err_msg("Program - %s connection %s", argv[0], broadcastIP);

        sleep(3);   /* Avoids flooding the network */
    }
    /* NOT REACHED */
}
// -----------------------------------------  //
// Interrupt Handler                          //
// In this case will catch 3 soft errors      //
void InterruptSignalHandler(int signalType)
{
    interruptCnt++;
    if (interruptCnt < 3) {
       printf("Interrupt %d Received. Strike %d \n", interruptCnt, interruptCnt);
    } else {
       printf("Interrupt %d Received. Strike %d - Your out!!\n", interruptCnt, interruptCnt);
       exit(1);
    }
}
