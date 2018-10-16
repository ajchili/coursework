#include <stdio.h>      	/* for printf() and fprintf() */
#include <stdlib.h>     	/* for atoi() and exit() */
#ifdef _WINDOWS                 /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <winsock2.h>           /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <ws2tcpip.h>           /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#else
//
// changes needed here
#include <arpa/inet.h>  	/* for sockaddr_in and inet_ntoa() */
#include <sys/socket.h> 	/* for socket(), bind(), and connect() */
#include <string.h>     	/* for memset() */
#include <unistd.h>     	/* for close() */
#include <netinet/in.h>     /* for sockaddr_in6*/
#endif

#define MAXPENDING 5    	/* Maximum outstanding connection requests */

void DieWithError(char *errorMessage);  /* Error handling helper function */
void HandleClientTCP(int clntSocket);   /* TCP client handling function */

int main(int argc, char *argv[])
{
    int servSock_d;                     /* Socket descriptor for server */
    int clntSock_d;                     /* Socket descriptor for client */
//
// changes needed here
    struct sockaddr_in6 echoServAddr;    /* Local address */
    struct sockaddr_in6 echoClntAddr;    /* Client address */
    char ip6_str[INET6_ADDRSTRLEN];/* for IPV6 hex string */
    unsigned short echoServPort;        /* Server port */
    unsigned int clntLen;               /* Length of client address data structure */

    /* ------Step 0 check user input ------ */
    /* Test for correct number of arguments from user */
    if (argc != 2) {     
        fprintf(stderr, "Usage:  %s <Server Port>\n", argv[0]);
        exit(1);
    }

    echoServPort = atoi(argv[1]);  	/* First arg:  should be local port */

    /* ------Step 1 create the socket ------- */
    /* Create socket for incoming connections */
//
// changes needed here
    if ((servSock_d = socket(PF_INET6, SOCK_STREAM, IPPROTO_TCP)) < 0)
        DieWithError("socket() failed");
      
    /* Construct local address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr));   /* Zero out structure */
    
    echoServAddr.sin6_family = AF_INET6;                /* Internet address family */
    echoServAddr.sin6_addr = in6addr_any; /* Any incoming interface */
    echoServAddr.sin6_port = htons(echoServPort);      /* Local port */

    /* ------Step 2 bind the connection ip:port ------- */
    /* Bind to the local address */
    if (bind(servSock_d, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) < 0)
        DieWithError("bind() failed");

    /* ------Step 3 listen to the socket for a connection ------- */
    /* Establish the socket to listen for incoming connections */
    if (listen(servSock_d, MAXPENDING) < 0)
        DieWithError("listen() failed");

    for (;;) /* Infinite Loop - run until process killed */
    {
        /* Set the size of the in-out parameter */
        clntLen = sizeof(echoClntAddr);

    /* ------Step 4 wait and then accept a connection ------- */
        /* Wait for a client to connect */
        if ((clntSock_d = accept(servSock_d, (struct sockaddr *) &echoClntAddr, &clntLen)) < 0)
            DieWithError("accept() failed");

        /* clntSock_d is connected to a client! */
//
// changes needed here
        printf("Handling client %s\n", inet_ntop(AF_INET6, &echoClntAddr.sin6_addr, ip6_str, sizeof(ip6_str)));

    /* ------Step 5 recv from the client ------- */
    /* ------Step 6 send to the client --------- */
        HandleClientTCP(clntSock_d);
    } 
    /* ------Step 7 implied close when program is terminated ------- */
}
