#include <stdio.h>    /* for printf() and fprintf() */
#include <stdlib.h>   /* for atoi() and exit() */
#ifdef _WIN32         /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <winsock2.h> /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <ws2tcpip.h> /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#else
#include <arpa/inet.h>  /* for sockaddr_in and inet_ntoa() */
#include <sys/socket.h> /* for socket(), bind(), and connect() */
#include <string.h>     /* for memset() */
#include <unistd.h>     /* for close() */
#include <time.h>       /* for time functions */
#endif

#define RCVBUFSIZE 80 /* Arbitrary size of receive buffer */

void DieWithError(char *errorMessage); /* Error handling helper function */

void HandleClientUDP(int servSock)
{
  struct sockaddr_in echoClntAddr; /* Client address */
  unsigned int clntLen;            /* Length of client address data structure */
  char echoBuffer[RCVBUFSIZE];     /* Buffer for echo string */
  int rcvMsgSize;                  /* Size of received message */

  clntLen = sizeof(echoClntAddr);

  if ((rcvMsgSize = recvfrom(servSock, echoBuffer, RCVBUFSIZE, 0, (struct sockaddr *)&echoClntAddr, &clntLen)) < 0)
    DieWithError("recvfrom() failed");

  /* Ensures message received is a supported command */
  if (echoBuffer[0] == 'd' &&
      echoBuffer[1] == 'a' &&
      echoBuffer[2] == 't' &&
      echoBuffer[3] == 'e')
  {
    /* Get server current date/time */
    time_t t = time(NULL);
    struct tm *tm = localtime(&t);
    char time[64];
    strftime(time, sizeof(time), "%c", tm);

    /* Sends server date/time to client and handles any transmission error */
    if (sendto(servSock, time, sizeof(time), 0, (struct sockaddr *)&echoClntAddr, sizeof(echoClntAddr)) != sizeof(time))
      DieWithError("sendto() sent a different number of bytes than expected");
  }
  else
  {
    char message[RCVBUFSIZE] = "Action not supported";
    /* Notifies client of unsupported action and handles any transmission error */
    if (sendto(servSock, message, RCVBUFSIZE, 0, (struct sockaddr *)&echoClntAddr, sizeof(echoClntAddr)) != RCVBUFSIZE)
      DieWithError("sendto() sent a different number of bytes than expected");
    else
    {
      printf("ERROR - Unsupported client request: [%.*s] \n", rcvMsgSize, echoBuffer);
    }
  }
}