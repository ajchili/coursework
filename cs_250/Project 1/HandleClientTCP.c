#include <stdio.h>      	/* for printf() and fprintf() */
#ifdef _WIN32                   /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <winsock2.h>           /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <ws2tcpip.h>           /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#else
#include <sys/socket.h> 	/* for recv() and send() */
#include <unistd.h>     	/* for close() */
#endif

#define RCVBUFSIZE 80   	/* Arbitrary size of receive buffer */

void DieWithError(char *errorMessage);    /* Error handling function */
int CaesarCipher(int option, char str[]); /* String decryption */

/**
 * type is the server action that will be handled within this fork
 * 1 - Quote of the day
 * 2 - Country Database
 * Any other number - Defaults to 1
 */
void HandleClientTCP(int clntSocket, int type)
{
    char echoBuffer[RCVBUFSIZE]; /* Buffer for echo string */
    int recvMsgSize;             /* Size of received message */

    /* ------Step 5 recv from the client ------- */
    /* Receive message from client */
    if ((recvMsgSize = recv(clntSocket, echoBuffer, RCVBUFSIZE, 0)) < 0)
        DieWithError("recv() failed");

    /* Convert encrypted message to readable message */
    CaesarCipher(2, echoBuffer);
    recvMsgSize = sizeof(echoBuffer);
    
    char okMessage[RCVBUFSIZE] = "OK";
    char errorMessage[RCVBUFSIZE] = "Action not supported";
    CaesarCipher(1, okMessage);
    CaesarCipher(1, errorMessage);
    int okMessageSize = sizeof(okMessage);
    int errorMessageSize = sizeof(errorMessage);

    if (type == 2 &&
        echoBuffer[0] == 'c' &&
        echoBuffer[1] == 'o' &&
        echoBuffer[2] == 'u' &&
        echoBuffer[3] == 'n' &&
        echoBuffer[4] == 't' &&
        echoBuffer[5] == 'r' &&
        echoBuffer[6] == 'y') {
        if (send(clntSocket, okMessage, okMessageSize, 0) != okMessageSize)
            DieWithError("send() failed");

        if ((recvMsgSize = recv(clntSocket, echoBuffer, RCVBUFSIZE, 0)) < 0)
            DieWithError("recv() failed");
        
        // TODO: get country from file
    } else if (echoBuffer[0] == 'q' &&
        echoBuffer[1] == 'u' &&
        echoBuffer[2] == 'o' &&
        echoBuffer[3] == 't' &&
        echoBuffer[4] == 'e') {
        if (send(clntSocket, okMessage, okMessageSize, 0) != okMessageSize)
            DieWithError("send() failed");

        // TODO: get quote from file
    } else {
        /* Notifies client of unsupported action and handles any transmission error */
        if (send(clntSocket, errorMessage, errorMessageSize, 0) != errorMessageSize)
            DieWithError("send() failed");
    }

    /* ------Step 7 close when program is terminated ------- */
    /* Close client socket and clean up resources*/
    close(clntSocket);
    printf("closed\n");
#ifdef _WIN32 				/* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
    WSACleanup()
#endif
}
