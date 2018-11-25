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

void DieWithError(char *errorMessage);    /* Error handling function */
int CaesarCipher(int option, char str[]); /* String encryption */

int getServerCountryData() {
  int sock_Descr;                  /* Socket descriptor */
  struct sockaddr_in echoServAddr; /* Echo server address */
  unsigned short echoServPort;     /* Echo server port */
  char *servIP;                    /* Server IP address (dotted quad) */
  char echoBuffer[RCVBUFSIZE];     /* Buffer for echo string */
  int echoStringLen;               /* Length of string to echo */

  servIP = "0.0.0.0";                     /* Server IP address (dotted quad) */
  char echoString[] = "country";          /* String to send to echo server */
  CaesarCipher(1, echoString);
  echoServPort = 3000;

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
    printf("Waiting for response from server...\n");
      
  if (recv(sock_Descr, echoBuffer, RCVBUFSIZE, 0) < 0)
    DieWithError("recv() failed");
      
  CaesarCipher(2, echoBuffer);
  if (echoBuffer[0] == 'O' && echoBuffer[1] == 'K') {
    char countryCode[RCVBUFSIZE];

    printf("Please enter the code of the Country you would like to get information about: ");
    scanf("%s", countryCode);
    int countryCodeLength = sizeof(countryCode);
    
    if (send(sock_Descr, countryCode, countryCodeLength, 0) != countryCodeLength)
      DieWithError("send() failed");
    // else printf("%s", countryCode);
  } else {
    printf("%s", echoBuffer);
  }

  /* ------Step 5 close connection with server and release resources ------ */
  close(sock_Descr);
  return 0;
}
