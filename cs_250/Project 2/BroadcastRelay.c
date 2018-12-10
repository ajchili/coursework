#include <stdio.h>      /* for printf() and fprintf() */
#include <sys/socket.h> /* for socket() and bind() */
#include <arpa/inet.h>  /* for sockaddr_in */
#include <stdlib.h>     /* for atoi() and exit() */
#include <string.h>     /* for memset() */
#include <unistd.h>     /* for close() */
#include <sys/types.h>
#include <signal.h>

#define MAXRECVSTRING 255 /* Longest string to receive */
#define MAXRECVSTRINGS 99 /* Maximum number of messages that can be received */

void DieWithError(char *errorMessage);     /* External error handling function */
unsigned long NameResolution(char name[]); /* Obtains IPV4 address from host name */
unsigned short ServiceResolution(char service[], char protocol[]);

void sendMessageToDomain(char domain, char *message)
{
  int sock;                         /* Socket */
  struct sockaddr_in broadcastAddr; /* Broadcast address */
  int broadcastPermission;          /* Socket opt to set permission to broadcast */
  unsigned int sendStringLen;       /* Length of string to broadcast */
  pid_t fork_ProcessID;             /* Fork Process ID from fork() */

  /* Create fork */
  if ((fork_ProcessID = fork()) < 0)
    DieWithError("fork() failed");
  else if (fork_ProcessID == 0)
  {
    /* Create socket for sending/receiving datagrams */
    if ((sock = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0)
      DieWithError("socket() failed");

    /* Set socket to allow broadcast */
    broadcastPermission = 1;
    if (setsockopt(sock, SOL_SOCKET, SO_BROADCAST, (void *)&broadcastPermission, sizeof(broadcastPermission)) < 0)
      DieWithError("setsockopt() failed");

    /* Construct local address structure */
    memset(&broadcastAddr, 0, sizeof(broadcastAddr));                  /* Zero out structure */
    broadcastAddr.sin_family = AF_INET;                                /* Internet address family */
    broadcastAddr.sin_addr.s_addr = NameResolution("CS250MsgHost");    /* Broadcast IP address */
    broadcastAddr.sin_port = ServiceResolution("CS250MsgServ", "udp"); /* Broadcast port */

    char *messageWithDomain;                         /* Message with domain prepended to it */
    messageWithDomain = malloc(strlen(message) + 5); /* Set length of new message */
    messageWithDomain[0] = domain;                   /* Set domain */
    strcat(messageWithDomain, " - ");                /* Add spacer */
    strcat(messageWithDomain, message);              /* Add message */
    sendStringLen = strlen(messageWithDomain);       /* Find length of message */
    /* Run 3 times to send message to server but prevent overflow of messages */
    for (int i = 0; i < 3; i++)
    {
      /* Broadcast message in datagram to clients every 3 seconds*/
      if (sendto(sock, messageWithDomain, sendStringLen, 0, (struct sockaddr *)&broadcastAddr, sizeof(broadcastAddr)) != sendStringLen)
        DieWithError("sendto() sent a different number of bytes than expected");

      sleep(3); /* Avoids flooding the network */
    }
    close(sock);
    exit(0);
  }
}

void readMessagesOnDomain(char domain)
{
  int sock;                           /* Socket descriptor */
  struct sockaddr_in echoServAddr;    /* Echo server address */
  struct sockaddr_in fromAddr;        /* Source address of echo */
  unsigned int fromSize;              /* In-out of address size for recvfrom() */
  char *echoString;                   /* String to send to echo server */
  char echoBuffer[MAXRECVSTRING + 1]; /* Buffer for receiving echoed string */
  int echoStringLen;                  /* Length of string to echo */
  int respStringLen;                  /* Length of received response */

  if ((sock = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0)
    DieWithError("socket() failed");

  echoString = "ready";
  echoStringLen = strlen(echoString); /* Determine input length */

  /* Construct the server address structure */
  memset(&echoServAddr, 0, sizeof(echoServAddr));                         /* Zero out structure */
  echoServAddr.sin_family = AF_INET;                                      /* Internet addr family */
  echoServAddr.sin_addr.s_addr = NameResolution("CS250MsgHost");          /* Server IP address */
  echoServAddr.sin_port = ServiceResolution("CS250MsgServSender", "udp"); /* Server port */

  /* Send received datagram back to the client */
  if (sendto(sock, echoString, echoStringLen, 0, (struct sockaddr *)&echoServAddr, sizeof(echoServAddr)) != echoStringLen)
    DieWithError("sendto() sent a different number of bytes than expected");
  else
  {
    /* Print on the client what was sent to the server. */
    printf("Waiting for response from server...\n");
  }

  fromSize = sizeof(echoServAddr);

  for (;;)
  {
    /* Block until receive message from a client */
    if ((respStringLen = recvfrom(sock, echoBuffer, MAXRECVSTRING, 0, (struct sockaddr *)&fromAddr, &fromSize)) < 0)
      DieWithError("recvfrom() failed");

    if (strcmp(echoBuffer, "end") == 0)
      break;
    else if (echoBuffer[0] == domain || echoBuffer[0] == 'C')
      /* Print on the client what was received from the server. */
      printf("%s\n", echoBuffer + 4);
  }

  /* ------Step 5 close connection with server and release resources ------ */
  close(sock);
}