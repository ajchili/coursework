#include <stdio.h>
#include <stdlib.h>

void DieWithError(char *errorMessage);  /* Error handling function */
int handleInitialUserInput();           /* Handles user input for initialization of applciation */
void getServerDateTime();               /* Obtains date/time from server */

int main(void) {
  system("@cls||clear");                    /* Clears screen */
  
  for (;;) {
    int selection = handleInitialUserInput(); /* Determins what action user wants to perform */
    printf("\n");

    switch (selection) {                      /* Handles the selected action */
      case 1:                                 /* Case 1 - Get server Date/Time */
        getServerDateTime();
        break;
      case 2:                                 /* Case 2 - Get Quote of the Date */
        break;
      case 3:                                 /* Case 3 - Get Country data */
        break;
    }

    printf("\n\n\n\n");
  }

  return 0;
}

/**
 * Handles initial user input. Determins what action the user wants to perform.
 * 
 * Does not take in any parameters but returns the action the user wants to perform.
 */
int handleInitialUserInput() {
  int selection = 0;

  printf("Hello, what would you like do?\n");
  printf("1 - Get the Date/Time\n");
  printf("2 - Get the Quote of the Date\n");
  printf("3 - Look up Country Code\n");

  scanf("%d", &selection);

  if (selection < 1 || selection > 3) {
    DieWithError("The selection you made is not valid!");
  }

  return selection;
}
