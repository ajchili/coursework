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

#define RCVBUFSIZE 80   /* Size of receive buffer */

void DieWithError(char *errorMessage);  /* Error handling function */

int main(int argc, char *argv[])
{
    int sock_Descr;                  /* Socket descriptor */
    struct sockaddr_in echoServAddr; /* Echo server address */
    unsigned short echoServPort;     /* Echo server port */
    char *servIP;                    /* Server IP address (dotted quad) */
    char *echoString;                /* String to send to echo server */
    char echoBuffer[RCVBUFSIZE];     /* Buffer for echo string */
    unsigned int echoStringLen;      /* Length of string to echo */
    int bytesRcvd, totalBytesRcvd;   /* Bytes read in single recv() 
                                        and total bytes read */

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

    /* ------Step 1 create socket --------------- */
    /* Create a reliable, stream socket using TCP */
    if ((sock_Descr = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
        DieWithError("socket() failed");


    /* Construct the server address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr));     /* Zero out structure */
    echoServAddr.sin_family      = AF_INET;             /* Internet address family */
    echoServAddr.sin_addr.s_addr = inet_addr(servIP);   /* Server IP address */
    echoServAddr.sin_port        = htons(echoServPort); /* Server port */

    /* ------Step 2 connect to server ------------ */
    /* Establish the connection to the echo server */

    if (connect(sock_Descr, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) < 0)
        DieWithError("connect() failed");

    echoStringLen = strlen(echoString);          /* Determine input length */

    /* ------Step 3 send message to server ------- */
    /* Send the string to the server */

    if (send(sock_Descr, echoString, echoStringLen, 0) != echoStringLen)
        DieWithError("send() failed");
    else
        printf("Sent message to the server: [%s] \n", echoString);

        
    /* ------Step 4 recv message from server ------ */
    /* Receive the same string back from the server */
    totalBytesRcvd = 0;
    printf("Received back from Server: [");                /* Setup to print the echoed string */
    while (totalBytesRcvd < echoStringLen)
    {

    if ((totalBytesRcvd = recv(sock_Descr, echoBuffer, RCVBUFSIZE, 0)) < 0)
        DieWithError("recv() failed");

    printf("%s", echoBuffer);
    }

    printf("]\n");    /* Print a final linefeed */

    /* ------Step 5 close connection with server and release resources ------ */
    close(sock_Descr);
#ifdef _WIN32 				/* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
    WSACleanup()                        /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#endif
    exit(0);
}
