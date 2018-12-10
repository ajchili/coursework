#include <stdio.h>      /* for printf() and fprintf() */
#include <sys/socket.h> /* for socket() and bind() */
#include <arpa/inet.h>  /* for sockaddr_in */
#include <stdlib.h>     /* for atoi() and exit() */
#include <string.h>     /* for memset() */
#include <unistd.h>     /* for close() */

#define MAXRECVSTRING 255 /* Longest string to receive */
#define MAXRECVSTRINGS 99 /* Maximum number of messages that can be received */

void DieWithError(char *errorMessage);     /* External error handling function */
unsigned long NameResolution(char name[]); /* Obtains IPV4 address from host name */
unsigned short ServiceResolution(char service[], char protocol[]);
void broadcastSender(char *messages[]);
void broadcastReceiver(char *messages[]);

int main(void)
{
  char *messages[MAXRECVSTRINGS]; /* Array of seen messages */
  memset(&messages, 0, sizeof(messages)); 
  pid_t fork_ProcessID;           /* Fork Process ID from fork() */

  /* Create fork */
  if ((fork_ProcessID = fork()) < 0)
    DieWithError("fork() failed");
  else if (fork_ProcessID == 0)
    broadcastReceiver(messages);

  /* Create fork */
  if ((fork_ProcessID = fork()) < 0)
    DieWithError("fork() failed");
  else if (fork_ProcessID == 0)
    broadcastSender(messages);
}

void broadcastSender(char *messages[])
{
  int sock;                        /* Socket descriptor */
  struct sockaddr_in echoServAddr; /* Local address */
  struct sockaddr_in echoClntAddr; /* Client address */
  unsigned int clntLen;            /* Length of client address data structure */

  /* ------Step 1 create the socket ------- */
  if ((sock = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0)
    DieWithError("socket() failed");

  /* Construct local address structure */
  memset(&echoServAddr, 0, sizeof(echoServAddr));                         /* Zero out structure */
  echoServAddr.sin_family = AF_INET;                                      /* Internet address family */
  echoServAddr.sin_addr.s_addr = htonl(INADDR_ANY);                       /* Any incoming interface */
  echoServAddr.sin_port = ServiceResolution("CS250MsgServSender", "udp"); /* Local port */

  /* ------Step 2 bind the connection ip:port ------- */
  /* Bind to the local address */
  if (bind(sock, (struct sockaddr *)&echoServAddr, sizeof(echoServAddr)) < 0)
    DieWithError("bind() failed");

  for (;;) /* Infinite Loop - run until process killed */
  {
    /* Set the size of the in-out parameter */
    clntLen = sizeof(echoClntAddr);

    char buffer[MAXRECVSTRINGS];
    if (recvfrom(sock, buffer, MAXRECVSTRINGS, 0, (struct sockaddr *)&echoClntAddr, &clntLen) < 0)
      DieWithError("recvfrom() failed");

    /* ------Step 4 send to the socket  ------- */
    /* Send received datagram back to the client */
    for (int i = 0; i < MAXRECVSTRINGS; i++)
    {
      if (messages[i] != NULL)
      {
        if (sendto(sock,
                   messages[i],
                   255,
                   0,
                   (struct sockaddr *)&echoClntAddr,
                   sizeof(echoClntAddr)) != 255)
          DieWithError("sendto() sent a different number of bytes than expected");
      }
      else
      {
        char *message = "end";
        int messageSize = sizeof(message);
        if (sendto(sock,
                   message,
                   messageSize,
                   0,
                   (struct sockaddr *)&echoClntAddr,
                   sizeof(echoClntAddr)) != messageSize)
          DieWithError("sendto() sent a different number of bytes than expected");

        break;
      }
    }
  }
}

void broadcastReceiver(char *messages[])
{
  int sock;                           /* Socket */
  struct sockaddr_in broadcastAddr;   /* Broadcast Address */
  char recvString[MAXRECVSTRING + 1]; /* Buffer for received string */
  int recvStringLen;                  /* Length of received string */

  /* Create a best-effort datagram socket using UDP */
  if ((sock = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0)
    DieWithError("socket() failed");

  /* Construct bind structure */
  memset(&broadcastAddr, 0, sizeof(broadcastAddr));                  /* Zero out structure */
  broadcastAddr.sin_family = AF_INET;                                /* Internet address family */
  broadcastAddr.sin_addr.s_addr = htonl(INADDR_ANY);                 /* Any incoming interface */
  broadcastAddr.sin_port = ServiceResolution("CS250MsgServ", "udp"); /* Broadcast port */

  /* Bind to the broadcast port */
  if (bind(sock, (struct sockaddr *)&broadcastAddr, sizeof(broadcastAddr)) < 0)
    DieWithError("bind() failed");

  int length = 0;
  for (;;)
  {
    /* Receive a single datagram from the server */
    if ((recvStringLen = recvfrom(sock, recvString, MAXRECVSTRING, 0, NULL, 0)) < 0)
      DieWithError("recvfrom() failed");

    for (int i = 0; i < MAXRECVSTRINGS; i++)
    {
      if (messages[i] == NULL)
      {
        messages[length] = recvString;
        length++;
        break;
      }
      else if (strcmp(messages[i], recvString) == 0)
        break;
    }
  }

  close(sock);
  exit(0);
}