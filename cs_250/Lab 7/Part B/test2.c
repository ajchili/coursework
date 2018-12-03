#include <stdio.h>      	/* for printf() and fprintf() */
#include <stdlib.h>      	/* for  exit */
#include <strings.h>            /* bzero */
#include <ctype.h>            	/* toupper  */
#ifdef _WINDOWS                 /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <winsock2.h>           /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#include <ws2tcpip.h>           /* IF ON A WINDOWS PLATFORM YOU WILL HAVE TO CHECK THIS */
#else
#include <arpa/inet.h>  	/* for sockaddr_in and inet_ntoa() */
#include <netdb.h>  	        /* for IPV6,  SCTP support         */
#endif

void DieWithError(char *errorMessage);  /* Error handling helper function */

int main(int argc, char **argv) {
	struct addrinfo 	*addptr, hints;
        char                    *servIP, *servPort, *proto, *echoString; 
        char                    result[INET_ADDRSTRLEN], result6[INET6_ADDRSTRLEN];
        struct sockaddr_in 	*sin ;
        struct sockaddr_in6 	*sin6 ;

        bzero (&hints, sizeof(struct addrinfo));

        if ((argc < 6))    /* Test for correct number of arguments */
        {
          printf("Wrong number of arguments\n");
          fprintf(stderr, "Usage: %s <Server IP> <Echo Port> <IP Version [4 or 6]> <protocol [UDP,TCP, or SCTP]> <Echo Message> \n",
               argv[0]);
          exit(1);
        }

       servIP = argv[1];             /* First arg: server IP address (dotted quad) */
       servPort = argv[2];           /* First arg: server IP address (dotted quad) */
       proto = argv[4];		     /* Capture the protocol input */
       echoString = argv[5];         /* Second arg: string to echo */

       hints.ai_flags = AI_CANONNAME;                /* always return canonical name */
       int ipVersion = atoi(argv[3]);
       if (ipVersion == 4 || ipVersion == 6) {
           if (ipVersion == 4)
       		hints.ai_family = AF_INET;                    /* AF_UNSPEC, AF_INET, AF_INET6, etc. */
           else
       		hints.ai_family = AF_INET6;                    /* AF_UNSPEC, AF_INET, AF_INET6, etc. */
       }
       else
        {
          printf("Wrong IPV argument %s \n", argv[3]);
          fprintf(stderr, "Usage: %s <Server IP> <Echo Port> <IP Version [4 or 6]> <protocol [UDP,TCP, or SCTP]> <Echo Message> \n",
               argv[0]);
          exit(1);
        }
       
       if (toupper(proto[0]) == 'U')
         hints.ai_socktype = SOCK_DGRAM;           /* 0, SOCK_STREAM, SOCK_DGRAM, etc. */
       else {
             if (toupper(proto[0]) == 'T')
                hints.ai_socktype = SOCK_STREAM;           /* 0, SOCK_STREAM, SOCK_DGRAM, etc. */
             else { if (toupper(proto[0]) == 'S')
                      hints.ai_socktype = SOCK_SEQPACKET;           /* 0, SOCK_STREAM, SOCK_DGRAM, etc. */
                    else {
                       printf("Wrong proto argument\n");
                       fprintf(stderr, "Usage: %s <Server IP> <Echo Port> <IP Version [4 or 6]> <protocol [UDP,TCP, or SCTP]> <Echo Message> \n",
                       argv[0]);
                       exit(1);
                       }
                  }
            }


	if ((getaddrinfo(servIP, servPort, &hints, &addptr)) != 0) {
               DieWithError("getaddrinfo() failed");
	}
//	printf("Flags Returned: %d\n", addptr->ai_flags);
//	printf("Family Returned: %d\n", addptr->ai_family);
//	printf("Socket Type Returned: %d\n", addptr->ai_socktype);
	printf("Echo String untouched: %s\n", echoString);
	printf("Protocol Returned: %d\n", addptr->ai_protocol);
	printf("official host name: %s\n", addptr->ai_canonname);

        if (ipVersion == 4) {
          sin = (struct sockaddr_in *) addptr->ai_addr;
          if ((inet_ntop(AF_INET, &sin->sin_addr, result, sizeof(result)) != NULL))
	      printf("Address Info  %s\n", result);
	  if (ntohs(sin->sin_port) != 0) 
	     printf("Port Info  %d\n", ntohs(sin->sin_port));
        }
        if (ipVersion == 6) {
          sin6 = (struct sockaddr_in6 *) addptr->ai_addr;
          if ((inet_ntop(AF_INET6, &sin6->sin6_addr, result6, sizeof(result6)) != NULL))
	      printf("IPV6 Address Info  %s\n", result6);
	  if (ntohs(sin6->sin6_port) != 0) 
	     printf("IPV6 Port Info  %d\n", ntohs(sin6->sin6_port));
        }
  return 0;
}
