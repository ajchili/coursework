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
  }
}

void readMessagesOnDomain(char domain)
{
  // TODO
}