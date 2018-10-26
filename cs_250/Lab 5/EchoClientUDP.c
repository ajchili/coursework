#include <stdio.h>      /* for printf() and fprintf() */
#include <sys/socket.h> /* for socket(), connect(), sendto(), and recvfrom() */
#include <arpa/inet.h>  /* for sockaddr_in and inet_addr() */
#include <stdlib.h>     /* for atoi() and exit() */
#include <string.h>     /* for memset() */
#include <unistd.h>     /* for close() */

#define ECHOMAX 255     /* Longest string to echo */

void DieWithError(char *errorMessage);  /* External error handling function */

int main(int argc, char *argv[])
{
    int sock;                        /* Socket descriptor */
    struct sockaddr_in echoServAddr; /* Echo server address */
    struct sockaddr_in fromAddr;     /* Source address of echo */
    unsigned short echoServPort;     /* Echo server port */
    unsigned int fromSize;           /* In-out of address size for recvfrom() */
    char *servIP;                    /* IP address of server */
    char *echoString;                /* String to send to echo server */
    char echoBuffer[ECHOMAX+1];      /* Buffer for receiving echoed string */
    int echoStringLen;               /* Length of string to echo */
    int respStringLen;               /* Length of received response */

    /* ------Step 0 check user input ------ */
    /* Test for correct number of arguments */
    if ((argc < 3) || (argc > 4))    /* Test for correct number of arguments */
    {
       fprintf(stderr, "Usage: %s <Server IP> <Echo Word> [<Echo Port>]\n",
               argv[0]);
       exit(1);
    }

    servIP = argv[1];             /* First arg: server IP address (dotted quad) */
    echoString = argv[2];         /* Second arg: string to echo */

    if (argc == 4)
        echoServPort = atoi(argv[3]); /* Use given port, if any */
    else
        echoServPort = 7;  /* 7 is the well-known port for the echo service */

    if ((sock = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0)
        DieWithError("socket() failed");

    echoStringLen = strlen(echoString);          /* Determine input length */

    /* Construct the server address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr));    /* Zero out structure */
    echoServAddr.sin_family = AF_INET;                 /* Internet addr family */
    echoServAddr.sin_addr.s_addr = inet_addr(servIP);  /* Server IP address */
    echoServAddr.sin_port   = htons(echoServPort);     /* Server port */

    /* Establish the connection to the echo server */
    if (connect(sock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) < 0)
        DieWithError("connect() failed");

    /* Send received datagram back to the client */
    if (sendto(sock, echoString, echoStringLen, 0, 
            (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) != echoStringLen)
        DieWithError("sendto() sent a different number of bytes than expected");
    else {
        /* Print on the client what was sent to the server. */
        printf("Sent to the server: [%.*s] \n", echoStringLen, echoString);
    }

    fromSize = sizeof(echoServAddr);

    /* Block until receive message from a client */
    if ((respStringLen = recvfrom(sock, echoBuffer, ECHOMAX, 0,
        (struct sockaddr *) &fromAddr, &fromSize)) < 0)
        DieWithError("recvfrom() failed");

    /* Print on the client what was received from the server. */
    printf("Received from the server: [%.*s] \n", echoStringLen, echoString);

    /* Check to see if original server is the same as the one the message was received from. */
    printf("Original server: %s - Request handled by: %s\n", inet_ntoa(echoServAddr.sin_addr), inet_ntoa(fromAddr.sin_addr));

    /* ------Step 5 close connection with server and release resources ------ */
    close(sock);

    #ifdef _WINDOWS	  /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
        WSACleanup()
    #endif
        exit(0);

}
