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

// Add Thread - changes
#include <pthread.h>
// Add Thread - changes

#define MAXPENDING 5    	/* Maximum outstanding connection requests */

// Add Thread - changes
void *ThreadMain(void *arg);            /* Main program of a thread */

/* Structure of arguments to pass to client thread */
struct ThreadArgs
{
    int clntSock_d;                      /* Socket descriptor for client */
};
// Add Thread - changes

void DieWithError(char *errorMessage);  /* Error handling helper function */
void HandleClientTCP(int clntSocket);   /* TCP client handling function */

int main(int argc, char *argv[])
{
    int servSock_d;                     /* Socket descriptor for server */
    int clntSock_d;                     /* Socket descriptor for client */
    struct sockaddr_in echoServAddr;    /* Local address */
    struct sockaddr_in echoClntAddr;    /* Client address */
    unsigned short echoServPort;        /* Server port */
    unsigned int clntLen;               /* Length of client address data structure */

// Add Thread - changes
    pthread_t threadID;              /* Thread ID from pthread_create() */
    struct ThreadArgs *threadArgs;   /* Pointer to argument structure for thread */
// Add Thread - changes

    /* ------Step 0 check user input ------ */
    /* Test for correct number of arguments from user */
    if (argc != 2) {     
        fprintf(stderr, "Usage:  %s <Server Port>\n", argv[0]);
        exit(1);
    }

    echoServPort = atoi(argv[1]);  	/* First arg:  should be local port */

    /* ------Step 1 create the socket ------- */
    /* Create socket for incoming connections */
    if ((servSock_d = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
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

        printf("Handling client %s\n", inet_ntoa(echoClntAddr.sin_addr));

    /* ------Step 5 recv from the client ------- */
    /* ------Step 6 send to the client --------- */
// Add Thread - changes
// Add Thread - changes
//        HandleClientTCP(clntSock_d);
        /* Create separate memory for client argument */
        if ((threadArgs = (struct ThreadArgs *) malloc(sizeof(struct ThreadArgs))) 
               == NULL)
            DieWithError("malloc() failed");
        threadArgs -> clntSock_d = clntSock_d;

        /* Create client thread */
        if (pthread_create(&threadID, NULL, ThreadMain, (void *) threadArgs) != 0)
            DieWithError("pthread_create() failed");
        printf("With thread %ld\n", (long int) threadID);
// Add Thread - changes
// Add Thread - changes

    } 
    /* ------Step 7 implied close when program is terminated ------- */
}

// Add thread changes
void *ThreadMain(void *threadArgs)
{
    int clntSock_d;                   /* Socket descriptor for client connection */

    /* Guarantees that thread resources are deallocated upon return */
    pthread_detach(pthread_self()); 

    /* Extract socket file descriptor from argument */
    clntSock_d = ((struct ThreadArgs *) threadArgs) -> clntSock_d;
    free(threadArgs);              /* Deallocate memory for argument */

    HandleClientTCP(clntSock_d);

    return (NULL);
}
// Add thread changes

