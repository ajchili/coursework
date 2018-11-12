#include <stdio.h>

int CaesarCipher(int option, char str[]);
void DieWithError(char *errorMessage);  /* Error handling function */

int main()
{
   char message[] = "Hello World Please Hide Me";
   printf("Passing the message to the CaesarHelper to encrypt\n");
   if(CaesarCipher(1,message)>0) 
      DieWithError("Encryption Error");
//   printf("encrypted is: %s\n", message);     // not needed as long as Caesar is working
   printf("Passing the message to the CaesarHelper to decrypt\n");
   if(CaesarCipher(2,message)>0) 
      DieWithError("Decryption Error");
//   printf("decrypted is: %s\n", message);     // not needed as long as Caesar is working

return 0;
}
