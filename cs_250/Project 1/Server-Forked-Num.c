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

#define RCVBUFSIZE 80   	/* Arbitrary size of receive buffer */
#define MAXPENDING 5    	/* Maximum outstanding connection requests */

void DieWithError(char *errorMessage);  /* Error handling helper function */
void HandleClientTCP(int clntSocket);   /* TCP client handling function */
void TCPProcessMain(int servSock);		/* Fork main function definition (TCP) */
void UDPProcessMain(int servSock);		/* Fork main function definition (UDP) */

int main(int argc, char *argv[])
{
    int TCPservSock_d;                  /* Socket descriptor for server */
    int UDPservSock_d;                  /* UPD Socket descriptor for server*/
    pid_t fork_ProcessID;		        /* Fork Process ID from fork() */
    unsigned int childProcessCount = 0; /* Number of child processes */
    unsigned int processLimit;          /* Number of child processes to create */
    struct sockaddr_in echoServAddr;    /* Local address */
    unsigned short echoServPort;        /* Server port */

    /* ------Step 0 check user input ------ */
    /* Test for correct number of arguments from user */
    if (argc != 3) {
        fprintf(stderr, "Usage:  %s <Server Port> <CHILD FORK LIMIT>\n", argv[0]);
        exit(1);
    }

    echoServPort = atoi(argv[1]);  	/* First arg:  should be local port */
    processLimit = atoi(argv[2]);  	/* Second arg:  should be limit for # of children */

    /* ------Step 1 create the socket ------- */
    /* Create socket for incoming connections (both TCP and UDP) */
    if ((TCPservSock_d = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
        DieWithError("socket() failed (TCP)");

    if ((UDPservSock_d = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0)
        DieWithError("socket() failed (UDP)");
      
    /* Construct local address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr));   /* Zero out structure */
    echoServAddr.sin_family = AF_INET;                /* Internet address family */
    echoServAddr.sin_addr.s_addr = htonl(INADDR_ANY); /* Any incoming interface */
    echoServAddr.sin_port = htons(echoServPort);      /* Local port */

    /* ------Step 2 bind the connection ip:port ------- */
    /* Bind to the local address */
    if (bind(TCPservSock_d, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) < 0)
        DieWithError("bind() failed (TCP)");

    if (bind(UDPservSock_d, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) < 0)
        DieWithError("bind() failed (UDP)");

    /* ------Step 3 listen to the socket for a connection ------- */
    /* Establish the socket to listen for incoming connections */
    if (listen(TCPservSock_d, MAXPENDING) < 0)
        DieWithError("listen() failed");

    for (childProcessCount=0; childProcessCount < processLimit; childProcessCount++) 
    {
        /* Fork child process and report any errors (TCP socket) */
        if ((fork_ProcessID = fork()) < 0)
            DieWithError("fork() failed");
        else if (fork_ProcessID == 0)  /* If this is the child process */
            TCPProcessMain(TCPservSock_d);
        
        /* Fork child process and report any errors (UDP socket) */
        if ((fork_ProcessID = fork()) < 0)
            DieWithError("fork() failed");
        else if (fork_ProcessID == 0)  /* If this is the child process */
            UDPProcessMain(UDPservSock_d);
    }
    exit(0);  /* The children will carry on */
}

void TCPProcessMain(int servSock)
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
    
    	printf("Handling client %s\n", inet_ntoa(echoClntAddr.sin_addr));

        printf("With child process: %d\n", (unsigned int) getpid());
        HandleClientTCP(clntSock);
    }
}

void UDPProcessMain(int servSock)
{
    int clntSock;                       /* Socket descriptor for client connection */
    struct sockaddr_in echoClntAddr;    /* Client address */
    unsigned int clntLen;               /* Length of client address data structure */
    char echoBuffer[RCVBUFSIZE];        /* Buffer for echo string */
    int rcvMsgSize;                     /* Size of received message */

    for (;;)
    {
        clntLen = sizeof(echoClntAddr);

        if ((rcvMsgSize = recvfrom(servSock, echoBuffer, RCVBUFSIZE, 0, (struct sockaddr *) &echoClntAddr, &clntLen)) < 0)
            DieWithError("recvfrom() failed");

        /* Ensures message received is a supported command */
        if (echoBuffer[0] == 'd' &&
            echoBuffer[1] == 'a' &&
            echoBuffer[2] == 't' &&
            echoBuffer[3] == 'e') {
            /* Get server current date/time */
            time_t t = time(NULL);
            struct tm *tm = localtime(&t);
            char time[64];
            strftime(time, sizeof(time), "%c", tm);
            
            /* Sends server date/time to client and handles any transmission error */
            if (sendto(servSock, time, sizeof(time), 0, (struct sockaddr *) &echoClntAddr, sizeof(echoClntAddr)) != sizeof(time))
                DieWithError("sendto() sent a different number of bytes than expected");
            else {
                printf("%s", time);
            }
        } else {
            char message[RCVBUFSIZE] = "Action not supported";
            /* Notifies client of unsupported action and handles any transmission error */
            if (sendto(servSock, message, RCVBUFSIZE, 0, (struct sockaddr *) &echoClntAddr, sizeof(echoClntAddr)) != RCVBUFSIZE)
                DieWithError("sendto() sent a different number of bytes than expected");
            else {
                printf("ERROR - Unsupported client request: [%.*s] \n", rcvMsgSize, echoBuffer);
            }
        }
    }
}
