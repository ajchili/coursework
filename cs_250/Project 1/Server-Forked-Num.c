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
#include <time.h>           /* for time functions */
#endif

#define MAXPENDING 5    	/* Maximum outstanding connection requests */

void DieWithError(char *errorMessage);              /* Error handling helper function */
void HandleClientTCP(int clntSocket, int type);     /* TCP client handling function */
void HandleClientUDP(int servSock);                 /* UDP client handling function */
void TCPProcessMain(int servSock, int type);		/* Fork main function definition (TCP) */
void UDPProcessMain(int servSock);		            /* Fork main function definition (UDP) */

int main(int argc, char *argv[])
{
    int MainTCPservSock_d;              /* Socket descriptor for main TCP server (country) */
    int SecondaryTCPservSock_d;              /* Socket descriptor for secondary TCP server (quotes) */
    int UDPservSock_d;                  /* UPD Socket descriptor for server*/
    pid_t fork_ProcessID;		        /* Fork Process ID from fork() */
    unsigned int childProcessCount = 0; /* Number of child processes */
    unsigned int processLimit;          /* Number of child processes to create */
    struct sockaddr_in mainServAddr;      /* Local address */
    struct sockaddr_in secondaryServAddr; /* Local secondary address */
    unsigned short echoServPort;          /* Server port */

    /* ------Step 0 check user input ------ */
    /* Test for correct number of arguments from user */
    if (argc != 2) {
        fprintf(stderr, "Usage:  %s <Server Port>\n", argv[0]);
        exit(1);
    }

    echoServPort = atoi(argv[1]);  	/* First arg: should be local port */
    processLimit = 3;  	            /* Limit for # of children */

    /* ------Step 1 create the socket ------- */
    /* Create socket for incoming connections (both TCP and UDP) */
    if ((MainTCPservSock_d = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
        DieWithError("socket() failed (TCP - country)");

    if ((SecondaryTCPservSock_d = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
        DieWithError("socket() failed (TCP - quote)");

    if ((UDPservSock_d = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0)
        DieWithError("socket() failed (UDP)");
      
    /* Construct local address structure */
    memset(&mainServAddr, 0, sizeof(mainServAddr));   /* Zero out structure */
    mainServAddr.sin_family = AF_INET;                /* Internet address family */
    mainServAddr.sin_addr.s_addr = htonl(INADDR_ANY); /* Any incoming interface */
    mainServAddr.sin_port = htons(echoServPort);      /* Local port */

    memset(&secondaryServAddr, 0, sizeof(secondaryServAddr)); /* Zero out structure */
    secondaryServAddr.sin_family = AF_INET;                   /* Internet address family */
    secondaryServAddr.sin_addr.s_addr = htonl(INADDR_ANY);    /* Any incoming interface */
    secondaryServAddr.sin_port = htons(echoServPort + 1);         /* Local port */

    /* ------Step 2 bind the connection ip:port ------- */
    /* Bind to the local address */
    if (bind(MainTCPservSock_d, (struct sockaddr *) &mainServAddr, sizeof(mainServAddr)) < 0)
        DieWithError("bind() failed (TCP - country)");

    if (bind(SecondaryTCPservSock_d, (struct sockaddr *) &secondaryServAddr, sizeof(secondaryServAddr)) < 0)
        DieWithError("bind() failed (TCP - quote)");

    if (bind(UDPservSock_d, (struct sockaddr *) &mainServAddr, sizeof(mainServAddr)) < 0)
        DieWithError("bind() failed (UDP)");

    /* ------Step 3 listen to the socket for a connection ------- */
    /* Establish the socket to listen for incoming connections */
    if (listen(MainTCPservSock_d, MAXPENDING) < 0)
        DieWithError("listen() failed (country)");

    if (listen(SecondaryTCPservSock_d, MAXPENDING) < 0)
        DieWithError("listen() failed (quote)");

    for (childProcessCount=0; childProcessCount < processLimit; childProcessCount++) 
    {
        /* Fork child process and report any errors (TCP socket for CSV database) */
        if ((fork_ProcessID = fork()) < 0)
            DieWithError("fork() failed");
        else if (fork_ProcessID == 0)  /* If this is the child process */
            TCPProcessMain(MainTCPservSock_d, 2);
    }

    /* Fork child process and report any errors (TCP socket for random quote) */
    if ((fork_ProcessID = fork()) < 0)
        DieWithError("fork() failed");
    else if (fork_ProcessID == 0)  /* If this is the child process */
        TCPProcessMain(SecondaryTCPservSock_d, 1);

    /* Fork child process and report any errors (UDP socket) */
    if ((fork_ProcessID = fork()) < 0)
        DieWithError("fork() failed");
    else if (fork_ProcessID == 0)  /* If this is the child process */
        UDPProcessMain(UDPservSock_d);
    exit(0);  /* The children will carry on */
}

void TCPProcessMain(int servSock, int type)
{
    int clntSock;                       /* Socket descriptor for client connection */
    struct sockaddr_in echoClntAddr;    /* Client address */
    unsigned int clntLen;               /* Length of client address data structure */

    for (;;)  /* Run forever */
    {
    	/* Set the size of the in-out parameter */
    	clntLen = sizeof(echoClntAddr);
    
    	/* Wait for a client to connect */
    	if ((clntSock = accept(servSock, (struct sockaddr *) &echoClntAddr, 
        	   &clntLen)) < 0)
        	DieWithError("accept() failed");
    
    	/* clntSock is connected to a client! */
        HandleClientTCP(clntSock, type);
    }
}

void UDPProcessMain(int servSock)
{
    for (;;) /* Run forever */
    {
        HandleClientUDP(servSock);
    }
}
