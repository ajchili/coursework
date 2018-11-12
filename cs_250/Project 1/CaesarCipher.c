#include <stdio.h>
#include <stdlib.h>

int CaesarCipher(int option, char str[])
{
   int i;
   switch(option)
   {
   case 1:
      printf("Option 1 = Encrypt the string.\n");
      for(i = 0; (i < 100 && str[i] != '\0'); i++)
        str[i] = str[i] + 3; //the key for encryption is 3 that is added to ASCII value
      printf("Encrypted string: %s\n", str);
      break;

   case 2:
      printf("Option 2 = Decrypt the string.\n");
      for(i = 0; (i < 100 && str[i] != '\0'); i++)
        str[i] = str[i] - 3; //the key for encryption is 3 that is subtracted to ASCII value
      printf("Decrypted string: %s\n", str);
      break;
   default:
      printf("\nError\n");
      return(1);
   }
   return (0);
}
