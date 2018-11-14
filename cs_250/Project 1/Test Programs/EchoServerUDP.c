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
#endif

#define ECHOMAX 255    		/* Maximum string to echo */

void DieWithError(char *errorMessage);  /* Error handling helper function */

int main(int argc, char *argv[])
{
    int genSock_d;                      /* Socket descriptor */
    struct sockaddr_in echoServAddr;    /* Local address */
    struct sockaddr_in echoClntAddr;    /* Client address */
    unsigned int clntLen;               /* Length of client address data structure */
    unsigned short echoServPort;        /* Server port */
    char echoBuffer[ECHOMAX];		/* Buffer for the echo string */
    int rcvMsgSize;			/* Size of the recieved message */

    /* ------Step 0 check user input ------ */
    /* Test for correct number of arguments from user */
    if (argc != 2) {     
        fprintf(stderr, "Usage:  %s <UDP Server Port>\n", argv[0]);
        exit(1);
    }

    echoServPort = atoi(argv[1]);  	/* First arg:  should be local port */

    /* ------Step 1 create the socket ------- */
    /* Create socket for incoming connections */
    if ((genSock_d = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0)
        DieWithError("socket() failed");
      
    /* Construct local address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr));   /* Zero out structure */
    echoServAddr.sin_family = AF_INET;                /* Internet address family */
    echoServAddr.sin_addr.s_addr = htonl(INADDR_ANY); /* Any incoming interface */
    echoServAddr.sin_port = htons(echoServPort);      /* Local port */

    /* ------Step 2 bind the connection ip:port ------- */
    /* Bind to the local address */
    if (bind(genSock_d, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) < 0)
        DieWithError("bind() failed");

    for (;;) /* Infinite Loop - run until process killed */
    {
        /* Set the size of the in-out parameter */
        clntLen = sizeof(echoClntAddr);

    /* ------Step 3 recieve from the socket  ------- */
        /* Block until receive message from a client */
        if ((rcvMsgSize = recvfrom(genSock_d, echoBuffer, ECHOMAX, 0,
            (struct sockaddr *) &echoClntAddr, &clntLen)) < 0)
            DieWithError("recvfrom() failed");

        printf("Handling client %s\n", inet_ntoa(echoClntAddr.sin_addr));

    /* ------Step 4 send to the socket  ------- */
        /* Send received datagram back to the client */
        if (sendto(genSock_d, echoBuffer, rcvMsgSize, 0, 
             (struct sockaddr *) &echoClntAddr, sizeof(echoClntAddr)) != rcvMsgSize)
            DieWithError("sendto() sent a different number of bytes than expected");
        else {
            /* Print on the server what the message was after echoing to client */
            printf("Received from the client: [%.*s] \n",rcvMsgSize, echoBuffer);
        }

    } 
    /* ------Step 5 implied close - will require cntrl-c or system interrupt to exit  ------- */
}
