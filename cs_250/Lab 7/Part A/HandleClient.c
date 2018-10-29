#include <stdio.h>      	/* for printf() and fprintf() */
#ifdef _WIN32                   /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <winsock2.h>           /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <ws2tcpip.h>           /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#else
#include <sys/socket.h> 	/* for recv() and send() */
#include <unistd.h>     	/* for close() */
#endif

#define RCVBUFSIZE 80   	/* Arbitrary size of receive buffer */

void DieWithError(char *errorMessage);  /* Error handling function */

void HandleClient(int clntSocket)
{
    char echoBuffer[RCVBUFSIZE];        /* Buffer for echo string */
    int recvMsgSize;                    /* Size of received message */

    /* ------Step 5 recv from the client ------- */
    /* Receive message from client */
    if ((recvMsgSize = recv(clntSocket, echoBuffer, RCVBUFSIZE, 0)) < 0)
        DieWithError("recv() failed");

    /* Send received string and receive again until end of transmission */
    while (recvMsgSize > 0)      /* zero indicates end of transmission */
    {
    
        /* ------Step 6 send to the client --------- */
        /* Echo message back to client */
        
        if (send(clntSocket, echoBuffer, recvMsgSize, 0) != recvMsgSize)
            DieWithError("send() failed");
        else {
            /* Print on the server what the message was after echoing to client */
            printf("Received from the client: [%.*s] \n",recvMsgSize, echoBuffer);
        }

        /* See if there is more data to receive */
        if ((recvMsgSize = recv(clntSocket, echoBuffer, RCVBUFSIZE, 0)) < 0)
            DieWithError("recv() failed");
    }

    /* ------Step 7 close when program is terminated ------- */
    /* Close client socket and clean up resources*/
    close(clntSocket);
#ifdef _WIN32 				/* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
    WSACleanup()
#endif
}
