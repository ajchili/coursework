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
   
//*************************************************************
// Coding - Part 1 - code the socket creation lines here
// Errors in the socket creation should be sent to DieWithError
//*************************************************************


    /* Construct the server address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr));     /* Zero out structure */
    echoServAddr.sin_family      = AF_INET;             /* Internet address family */
    echoServAddr.sin_addr.s_addr = inet_addr(servIP);   /* Server IP address */
    echoServAddr.sin_port        = htons(echoServPort); /* Server port */

    /* ------Step 2 connect to server ------------ */
    /* Establish the connection to the echo server */

//************************************************************
// Coding - Part 2 - code the connection to the socket
// Errors in the connection should be sent to DieWithError
//************************************************************

    echoStringLen = strlen(echoString);          /* Determine input length */

    /* ------Step 3 send message to server ------- */
    /* Send the string to the server */

//****************************************************************
// Coding - Part 3 - code the send across the socket of the string
// send to the socket and also code a printf showing what was sent
// Errors in the send should be sent to DieWithError
//****************************************************************

        
    /* ------Step 4 recv message from server ------ */
    /* Receive the same string back from the server */
    totalBytesRcvd = 0;
    printf("Received back from Server: [");                /* Setup to print the echoed string */
    while (totalBytesRcvd < echoStringLen)
    {

//*******************************************************************************
// Coding - Part 4 - code the recv in portions <= to the reciever buffer defined 
// within the preproc directives. also print the message recieved back from 
// the server socket. Should fit between the '[' printed before the while and 
// the ']' printed after the while  
// Errors in the recv should be sent to DieWithError
//*******************************************************************************

    }

    printf("]\n");    /* Print a final linefeed */

    /* ------Step 5 close connection with server and release resources ------ */
    close(sock_Descr);
#ifdef _WIN32 				/* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
    WSACleanup()                        /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#endif
    exit(0);
}
