#include <stdio.h> 	/* for printf/ fprintf */
#include <netdb.h> 	/* for gethostbyname() */ 

void DieWithError(char *errorMessage);
unsigned long NameResolutionv4(char name[]) {
	struct hostent *host; 	/* Structure containing host information */
	if ((host = gethostbyname(name)) == NULL)
		DieWithError("gethostbyname() failed ");

/* return the binary, network-byte-ordered address */
return * ((unsigned long *) host->h_addr_list [ 0] ) ;
}
