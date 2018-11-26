#include <stdio.h>
#include <stdlib.h>

#define ROT 3 /* Degree of rotation to occur (0 - 26) */

int CaesarCipher(int option, char str[])
{
   int i;
   switch (option)
   {
   case 1:
      for (i = 0; (i < 256 && str[i] != '\0'); i++)
         str[i] = str[i] + ROT; //the key for encryption is 3 that is added to ASCII value
      break;

   case 2:
      for (i = 0; (i < 256 && str[i] != '\0'); i++)
         str[i] = str[i] - ROT; //the key for encryption is 3 that is subtracted to ASCII value
      break;
   default:
      printf("\nError\n");
      return (1);
   }
   return (0);
}
