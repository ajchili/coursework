#include <stdio.h>    /* for printf() and fprintf() */
#ifdef _WIN32         /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <winsock2.h> /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <ws2tcpip.h> /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#else
#include <sys/socket.h> /* for recv() and send() */
#include <unistd.h>     /* for close() */
#endif

#define RCVBUFSIZE 80 /* Arbitrary size of receive buffer */

void DieWithError(char *errorMessage);                                  /* Error handling function */
int CaesarCipher(int option, char str[]);                               /* String decryption */
int getRandomQuote(char **arr, size_t *arr_len);                        /* Gets random quote */
int getQuoteStatus(int quote);                                          /* Returns status of quote */
void likeQuote(int quote);                                              /* Likes quote */
void dislikeQuote(int quote);                                           /* Dislikes quote */
int getCountryData(char code[RCVBUFSIZE], char **arr, size_t *arr_len); /* Obtains country data from country database with provided country id */

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

    /* Creates and encrypt ok and error messages for later use */
    char okMessage[RCVBUFSIZE] = "OK";
    char errorMessage[RCVBUFSIZE] = "Action not supported";
    CaesarCipher(1, okMessage);
    CaesarCipher(1, errorMessage);
    int okMessageSize = sizeof(okMessage);
    int errorMessageSize = sizeof(errorMessage);

    /* Determines fork type and if requested action is permitable */
    if (type == 2 && /* Checks that current fork is for obtaining country data and the request is valid */
        echoBuffer[0] == 'c' &&
        echoBuffer[1] == 'o' &&
        echoBuffer[2] == 'u' &&
        echoBuffer[3] == 'n' &&
        echoBuffer[4] == 't' &&
        echoBuffer[5] == 'r' &&
        echoBuffer[6] == 'y')
    {
        /* Sends ok message to client, indicating that if a country is found it will be sent */
        if (send(clntSocket, okMessage, okMessageSize, 0) != okMessageSize)
            DieWithError("send() failed");

        char countryId[RCVBUFSIZE];
        /* Obtains encrypted country id, expects size of 6 to comply with size of country ids in file */
        if ((recvMsgSize = recv(clntSocket, countryId, RCVBUFSIZE, 0)) < 0)
            DieWithError("recv() failed");

        /* Decrypts country id */
        CaesarCipher(2, countryId);
        char *country;        /* Country data */
        size_t countryLength; /* Length of country data */
        /* Searches country database file to get country data. */
        if (getCountryData(countryId, &country, &countryLength) == 1)
        {
            /* Encrypts country data */
            CaesarCipher(1, country);
            /* Sends country data to client */
            if (send(clntSocket, country, countryLength, 0) != countryLength)
                DieWithError("send() failed");
        }
        else
        {
            /* Enmcrypts and sends failure message. This only occurs if no country is found with the povided id. */
            char failedMessage[RCVBUFSIZE] = "A country with that specified id was unable to be found.";
            CaesarCipher(1, failedMessage);
            int failedMessageSize = sizeof(failedMessage);
            if (send(clntSocket, failedMessage, failedMessageSize, 0) != failedMessageSize)
                DieWithError("send() failed");
        }
    }
    else if (echoBuffer[0] == 'q' && /* Checks that current fork is for obtaining random quotes and the request is valid */
             echoBuffer[1] == 'u' &&
             echoBuffer[2] == 'o' &&
             echoBuffer[3] == 't' &&
             echoBuffer[4] == 'e')
    {
        /* Sends ok message to client, indicating a quote will be sent */
        if (send(clntSocket, okMessage, okMessageSize, 0) != okMessageSize)
            DieWithError("send() failed");

        /* Obtains random quote */
        char *quote;                                            /* Random quote */
        size_t quoteLength;                                     /* Length of random quote */
        int quoteNumber = getRandomQuote(&quote, &quoteLength); /* Random quote number */
        char quoteStatus[RCVBUFSIZE];                           /* Status of quote */
        int likes = getQuoteStatus(quoteNumber);                /* Number of likes a quote has */
        /* Validates that random quote does not have dislikes, if it does, obtain a new one. This can become an infinite loop if all quotes are disliked. Implementing a solution that only does this n times would be a good solution to prevent any infinite loop issues. */
        while (likes < 0)
        {
            quoteNumber = getRandomQuote(&quote, &quoteLength);
            likes = getQuoteStatus(quoteNumber);
        }
        CaesarCipher(1, quote);                                                                            /* Encrypts quote */
        sprintf(quoteStatus, "%d - %s\n", getQuoteStatus(quoteNumber), likes >= 0 ? "likes" : "dislikes"); /* Formats quote status */
        CaesarCipher(1, quoteStatus);                                                                      /* Encrypts quote status */

        /* Sends encrypted quote */
        if (send(clntSocket, quote, quoteLength, 0) != quoteLength)
            DieWithError("send() failed");

        /* Sends encrypted quote status */
        if (send(clntSocket, quoteStatus, RCVBUFSIZE, 0) != RCVBUFSIZE)
            DieWithError("send() failed");

        /* Receives user status action (like or dislike) */
        if ((recvMsgSize = recv(clntSocket, echoBuffer, RCVBUFSIZE, 0)) < 0)
            DieWithError("recv() failed");

        /* Decrypts user status action */
        CaesarCipher(2, echoBuffer);

        /* Handles user status action */
        if (echoBuffer[0] == 'L')
            likeQuote(quoteNumber);
        else if (echoBuffer[0] == 'D')
            dislikeQuote(quoteNumber);
    }
    else
    {
        /* Notifies client of unsupported action and handles any transmission error */
        if (send(clntSocket, errorMessage, errorMessageSize, 0) != errorMessageSize)
            DieWithError("send() failed");
    }

    /* Close client socket and clean up resources*/
    close(clntSocket);
#ifdef _WIN32 /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
    WSACleanup()
#endif
}