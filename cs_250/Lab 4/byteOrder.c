#include <stdio.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <arpa/inet.h>

typedef unsigned char *pointer;

void show_bytes(pointer start, size_t len){
  size_t i;
  for (i = 0; i < len; i++) {
    printf("Location is : %p \t What is stored: (hex)  0x%.2x  \n", start+i, start[i] );
  }
  printf("\n");
}

int main(int argc, char **argv)
{
	union {
	  short  s;
          char   c[sizeof(short)];
        } un;

	un.s = 0x0102;
        printf("\n\n");
	if (sizeof(short) == 2) {
		if (un.c[0] == 1 && un.c[1] == 2)
			printf("Your implementation is big-endian (hi-order byte starting address)\n\n");
		else if (un.c[0] == 2 && un.c[1] == 1)
			printf("Your implementation is little-endian (low-order byte starting address)\n\n");
		else
			printf("Your implementation is unknown \n");
	} else
		printf("Your unsigned short is not 2 bytes - sizeof(short) = %lu\n", sizeof(short));

       // second way to show
      int a = 15213;
      printf(" Int a = %d:\n",a);
      show_bytes ((pointer) &a, sizeof(int));

      unsigned short clientPort, message;
      unsigned int messageLength;

      clientPort = 1234;
     
      printf("This may change based upon your implementation:\n");
      printf(" Client port = %d\n", clientPort); 
      clientPort = htons(clientPort);
      printf(" Client port = %d\n", clientPort); 
      clientPort = ntohs(clientPort);
      printf(" Client port = %d\n", clientPort); 


exit(0);
}
