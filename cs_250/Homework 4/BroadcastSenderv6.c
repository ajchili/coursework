#include <stdio.h>      /* for printf() and fprintf() */
#include <sys/socket.h> /* for socket() and bind() */
#include <arpa/inet.h>  /* for sockaddr_in */
#include <stdlib.h>     /* for atoi() and exit() */
#include <string.h>     /* for memset() */
#include <unistd.h>     /* for close() */
#include <netinet/in.h> /* for IPV6 and SCTP support */

void DieWithError(char *errorMessage); /* External error handling function */

int main(int argc, char *argv[])
{
    int sock;                          /* Socket */
    struct sockaddr_in6 broadcastAddr; /* Broadcast address */
    char *broadcastIP;                 /* IP broadcast address */
    unsigned short broadcastPort;      /* Server port */
    char *sendString;                  /* String to broadcast */
    int broadcastPermission;           /* Socket opt to set permission to broadcast */
    unsigned int sendStringLen;        /* Length of string to broadcast */

    if (argc < 4) /* Test for correct number of parameters */
    {
        fprintf(stderr, "Usage:  %s <IP Address> <Port> <Send String>\n", argv[0]);
        exit(1);
    }

    broadcastIP = argv[1];         /* First arg:  broadcast IP address */
    broadcastPort = atoi(argv[2]); /* Second arg:  broadcast port */
    sendString = argv[3];          /* Third arg:  string to broadcast */

    /* Create socket for sending/receiving datagrams */
    if ((sock = socket(PF_INET6, SOCK_DGRAM, IPPROTO_UDP)) < 0)
        DieWithError("socket() failed");

    /* Set socket to allow broadcast */
    broadcastPermission = 1;
    if (setsockopt(sock, SOL_SOCKET, SO_BROADCAST, (void *)&broadcastPermission,
                   sizeof(broadcastPermission)) < 0)
        DieWithError("setsockopt() failed");

    /* Construct local address structure */
    memset(&broadcastAddr, 0, sizeof(broadcastAddr));                    /* Zero out structure */
    broadcastAddr.sin6_family = AF_INET6;                                /* Internet address family */
    if (inet_pton(AF_INET6, broadcastIP, &broadcastAddr.sin6_addr) <= 0) /* Broadcast IP address */
        DieWithError("inet_pton error for input IP address");
    broadcastAddr.sin6_port = htons(broadcastPort); /* Broadcast port */

    sendStringLen = strlen(sendString); /* Find length of sendString */
    for (;;)                            /* Run forever */
    {
        /* Broadcast sendString in datagram to clients every 3 seconds*/
        if (sendto(sock, sendString, sendStringLen, 0, (struct sockaddr *)&broadcastAddr, sizeof(broadcastAddr)) != sendStringLen)
            DieWithError("sendto() sent a different number of bytes than expected");

        sleep(3); /* Avoids flooding the network */
    }
    /* NOT REACHED */
}
